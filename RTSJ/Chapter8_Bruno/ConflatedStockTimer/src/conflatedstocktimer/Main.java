package conflatedstocktimer;
import javax.realtime.*;
public class Main {
    double update = 0.00;
    Object lock = new Object();

    class Conflater implements Runnable {
        double price = 0.00;
        Boolean updated = false;
        Boolean sendNextUpdate = false;
        
        class Listener extends RealtimeThread {
            Conflater conflater;
            public Listener(Conflater conflater) {
                this.conflater = conflater;
            }
            public void run() {
                try {
                    while ( true ) {
                        synchronized ( lock ) {
                            lock.wait();
                        }
                    
                        synchronized ( updated ) {
                            System.out.println("Listener: update=" + update);
                            price += update;
                            updated = true;
                            if ( sendNextUpdate )
                                conflater.run();
                        }
                    }    
                }
                catch ( Exception e ) { }
            }
        }
        
        public Conflater() {
            Listener listener = new Listener(this);
            listener.start();
        }

        public void run() {
            synchronized ( updated ) {
                if ( updated ) {
                    updateClients(price);
                    updated = false;
                    sendNextUpdate = false;
                }
                else {
                    sendNextUpdate = true;
                }
            }
        }

        private void updateClients(double newPrice) {
            System.out.println("Conflater: Updating clients:");
            System.out.println("Conflater:     price=" + newPrice);

            // Send update
            // ...
        }
    }

    class DataFeed extends RealtimeThread {
        private Object privLock = new Object();
        public void run() {
            // begin first period
            sendAndWait(0, 10.00); // starting price
            sendAndWait(2, -.02);
            sendAndWait(1, -.01);
            sendAndWait(1, +.01);
            sendAndWait(2, -.50);
            sendAndWait(4, 0.00); // just sleep for 4ms

            // begin second period
            sendAndWait(12, -.11);
        }

        private void sendAndWait(int interval, double change) {
            if ( interval > 0 ) {
                
                // Wait for elapsed time
                RelativeTime delay =
                    new RelativeTime(interval,0);
                try { RealtimeThread.sleep(delay); }
                    catch ( Exception e ) { }
            }

            if ( change != 0.00 ) {
                synchronized ( lock ) {
                    update = change;
                    lock.notify();
                }
            }
        }
    }

    public Main() {
        // Create the conflater handler
        Conflater conflater = new Conflater();
        AsyncEventHandler handler = 
            new AsyncEventHandler(conflater);
        
        // Create the conflation timer
        RelativeTime period = 
            new RelativeTime(10,0); // 10ms
        PeriodicTimer conflateTimer = 
                new PeriodicTimer(period, period, handler);        
        conflateTimer.enable();

        // Create the data feed
        DataFeed datafeed = new DataFeed();

        conflateTimer.start();
        datafeed.start();
    }
    
    public static void main(String[] args) {
        Main app = new Main();
    }
}
