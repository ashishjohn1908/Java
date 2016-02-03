
import com.sun.oss.trader.tradingengine.*;
import com.sun.oss.trader.data.*;

public class Main
{
    public static void main( String[] args )
    {
        try {
            // Create the Runnable worker classes
            //
            MarketManager marketMgr = new MarketManager();
            OrderManager orderMgr = new OrderManager(marketMgr);

            orderMgr.displayOrderBook();
            
            // Create and start Threads
            //
            Thread marketThread = new Thread(marketMgr);
            Thread orderThread = new Thread(orderMgr);
            marketThread.start();
            orderThread.start();

            while ( true ) {
                System.gc();
                Thread.sleep(1000);
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
