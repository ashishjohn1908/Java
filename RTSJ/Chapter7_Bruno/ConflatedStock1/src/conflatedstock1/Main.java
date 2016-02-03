package conflatedstock1;
import javax.realtime.*;
public class Main {
    Object lock = new Object();
    double update = 0.00;
    final RelativeTime PERIOD = new RelativeTime(10,0); // 10ms
    Clock rtClock = Clock.getRealtimeClock();
    
    class Conflater extends RealtimeThread {
        boolean updateOccured = false;
        RelativeTime timeout = PERIOD;
        AbsoluteTime startTime = null;
        double price = 0.00;

        public void run() {
            try { 
                System.out.println("Conflater started...");
                // Wait to receive starting price and begin conflation
                synchronized ( lock ) {
                    lock.wait();
                }
                
                // The first update is the starting price
                // Store the price and the time
                price = update;
                startTime = rtClock.getTime();
                System.out.println("Conflater received starting price");

                // Now wait for updates, apply them, and send
                // updated price to clients after conflated period
                while ( true ) {
                    synchronized ( lock ) {
                        HighResolutionTime.waitForObject(lock, timeout);
                        
                        AbsoluteTime current = rtClock.getTime(); 
                        RelativeTime elapsed = current.subtract(startTime);

                        // Check for update or timeout
                        if ( update != 0.00 ) {
                            // Apply update, and check time
                            updateOccured = true;
                            price += update;
                            System.out.println("Conflater: update " + update +
                                    ", Elapsed=" + elapsed);
                            update = 0.00;
                            timeout = PERIOD.subtract( (current.subtract(startTime)) );
                            //System.out.println("Time to wait: " + timeout.toString() );
                            if ( elapsed.getMilliseconds() > PERIOD.getMilliseconds() ) {
                                System.out.println("Conflater: update after period");
                                updateClients(price, elapsed);
                            }
                        }
                        else {
                            // Conflation period expired
                            if ( updateOccured ) {
                                // Send update and reset conflation period
                                updateClients(price, elapsed);
                                startTime = rtClock.getTime();
                            }
                        }
                    }
                }
            }
            catch ( Exception e ) { 
                e.printStackTrace();
            }
        }

        private void updateClients(double newPrice, RelativeTime lastUpdate) {
            System.out.println("Conflater: Updating clients:");
            System.out.println("Conflater:     price=" + newPrice);
            System.out.println("Conflater:     Time since last update:" +
                    lastUpdate);

            // Send update
            // ...

            // Reset some values
            timeout = PERIOD;
            updateOccured = false;
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
                try {
                    RelativeTime elapsed = new RelativeTime(interval,0);
                    synchronized ( privLock ) {
                        HighResolutionTime.waitForObject(privLock, elapsed);
                    }
                }
                catch ( Exception e ) { }
                //try {this.sleep(elapsed);} catch( Exception e ) { }
            }
            
            if ( change != 0.00 ) {
                update = change;
                synchronized ( lock ) {
                    lock.notify();
                }
            }
            
            /*
            try {
                synchronized ( this ) {
                    RelativeTime t = new RelativeTime(interval,0);
                    t.waitForObject(this, t);
                }
            }
            catch ( Exception e ) { }
             */ 
        }
    }
    
    public Main() {
        Conflater conflate = new Conflater();
        DataFeed datafeed = new DataFeed();
        
        conflate.start();
        datafeed.start();
    }
    
    public static void main(String[] args) {
        Main app = new Main();
    }
}
