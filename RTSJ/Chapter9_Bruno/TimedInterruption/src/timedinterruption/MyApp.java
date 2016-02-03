package timedinterruption;
import javax.realtime.*;
public class MyApp  {

    class MyInterruptible extends RealtimeThread 
      implements Interruptible {
        RelativeTime interval;
        int retries;
        Timed timed;
        boolean expired;

        public MyInterruptible(RelativeTime interval,
                              int retries) {
            this.interval = interval;
            this.retries = retries;
            this.timed = new Timed(interval);
        }

        public void run() {
            expired = false;

            // Attempt the operation maximum two times
            for ( int repeat = 0; repeat < retries; repeat++ ) {
                System.out.print("Trying critical operation...");
                timed.doInterruptible(this);
                if ( ! expired ) {
                    System.out.println("  SUCCEEDED");
                    return;
                }
                timed.resetTime(interval);
            }
        }

        public void run(AsynchronouslyInterruptedException aie) 
          throws AsynchronouslyInterruptedException {
            // Simulate a processor-intensive operation
            while ( true ) { 
                try {
                    RealtimeThread.sleep(1, 0);
                }
                catch ( Exception e) { }
            }
        }

        public void interruptAction(
                AsynchronouslyInterruptedException aie) {
            System.out.println("  FAILED");
            expired = true;
        }
    }
    
    public MyApp() {
        RelativeTime interval = 
                new RelativeTime(5000,0); // 5-second interval
        int retries = 3;

        MyInterruptible interuptible = 
                new MyInterruptible(interval, retries);
        
        interuptible.start();
    }
    
    public static void main(String[] args) {
        System.out.println("Starting up");
        MyApp app = new MyApp();
        //app.start();
    }
}
