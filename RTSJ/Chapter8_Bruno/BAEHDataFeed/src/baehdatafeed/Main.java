package baehdatafeed;
import java.util.*;
import javax.realtime.*;
public class Main {
    class Stock {
        public Stock (String s, double v) { 
            symbol = s; value = v;
            event = new AsyncEvent();
        } 
        public String symbol;
        public double value;
        public AsyncEvent event;
    }
    static Stock[] stocks = null;
    
    class Listener extends BoundAsyncEventHandler {
        String symbol;
        public Listener(String symbol) {
            this.symbol = symbol;
        }
        
        public void handleAsyncEvent() {
            double value = getValue(symbol);
            System.out.println(
                    "Listener(" + symbol + "):\tUpdate for " + 
                    symbol + "=" + value + " on thread: " + 
                    RealtimeThread.currentThread().getName());
        }
    }
    
    class DataFeed implements Runnable {
        Random random = new Random(System.currentTimeMillis());
        public void run() {
            int updates = 0;
            while ( updates++ < 100 ) {
                // Pick a stock at random
                int stock = random.nextInt(stocks.length);

                // Move up or down?
                boolean up = random.nextBoolean();

                // How much?
                int factor = random.nextInt(4) + 1;

                // Apply change
                double change = .01 * factor;
                if ( ! up )
                    change *= -1;
                stocks[stock].value += change;

                // Notify handler(s)
                stocks[stock].event.fire();
                
                RealtimeThread.waitForNextPeriod();
            }
        }
    }

    public Main() {
        // Create the Stock objects with event objects
        stocks[0] = new Stock("JAVA", 3.99);
        stocks[1] = new Stock("YHOO", 12.82);
        stocks[2] = new Stock("MOT", 4.52);
        stocks[3] = new Stock("MSFT", 20.37);
        stocks[4] = new Stock("AAPL", 94.50);

        /*
        stocks = new Stock[900];
        for ( int s = 0; s < stocks.length; s++ ) {
           stocks[s] = new Stock("STK"+s, 10.00 + (s*.01)); 
        }
        */ 

        // Create the listeners for these stocks
        for ( int s = 0; s < stocks.length; s++ ) {
            Listener l = new Listener(stocks[s].symbol);
            addListener(stocks[s].symbol, l);
        }
        
        // Start the DataFeed thread to generate updates
        PeriodicParameters rel =
                new PeriodicParameters(new RelativeTime(50,0));
        RealtimeThread datafeedRTT = 
                new RealtimeThread(
                    null, rel, null, 
                    null, null, new DataFeed() );
        datafeedRTT.start();
    }
    
    public double addListener(String symbol, AsyncEventHandler aeh) {
        for ( int s = 0; s < stocks.length; s++ ) {
            if ( stocks[s].symbol.equalsIgnoreCase(symbol)) {
                stocks[s].event.addHandler(aeh);
                return stocks[s].value;
            }
        }
        return -1; // symbol not found
    }
    
    public double getValue(String symbol) {
            for ( int s = 0; s < stocks.length; s++ ) {
            if ( stocks[s].symbol.equalsIgnoreCase(symbol))
                return stocks[s].value;
        }
        return -1; // symbol not found
    }
    
    public static void main(String[] args) {
        Main app = new Main();
    }
}
