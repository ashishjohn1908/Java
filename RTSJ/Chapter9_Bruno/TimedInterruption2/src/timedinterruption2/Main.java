package timedinterruption2;
import javax.realtime.*;
public class Main extends RealtimeThread {

    class MyInterruptible implements Interruptible {
        public void run(AsynchronouslyInterruptedException aie) 
          throws AsynchronouslyInterruptedException {
            // Simulate a processor-intensive operation
            System.out.print("Trying critical operation...");
            while ( true ) { 
                try {
                    RealtimeThread.sleep(1, 0);
                }
                catch ( Exception e) { }
            }
            //System.out.println("  SUCCESS");
        }

        public void interruptAction(
                AsynchronouslyInterruptedException aie) {
            System.out.println("  FAILED");
        }
    }

    public void run() {
        RelativeTime interval = 
                new RelativeTime(5000,0); // 5-second interval
        
        Timed timed = new Timed(interval); 

        MyInterruptible interuptible = 
                new MyInterruptible();
        
        timed.doInterruptible(interuptible);
    }
    
    public static void main(String[] args) {
        System.out.println("Starting up");
        Main app = new Main();
        app.start();
    }

}
