package safeterminate;
import javax.realtime.*;
public class Main {
    class Worker1 extends RealtimeThread implements Interruptible {
        AsynchronouslyInterruptedException aie;

        public Worker1() {
            this.aie = new AsynchronouslyInterruptedException();
            RelativeTime period = 
                    new RelativeTime(1000,0);
            ReleaseParameters rel = 
                    new PeriodicParameters(period);
            this.setReleaseParameters(rel);
        }

        public void run(AsynchronouslyInterruptedException aie)
                throws AsynchronouslyInterruptedException
        {
            while ( true ) {
                System.out.println("Worker1 doing work...");
                waitForNextPeriod();
            }
        }
        
        public void interruptAction(
                AsynchronouslyInterruptedException aie) {
            System.out.println("Worker1 interrupted");
        }
        
        public void run() {
            aie.doInterruptible(this);
        }
    }

    class Worker2 extends RealtimeThread implements Interruptible {
        AsynchronouslyInterruptedException aie;
        
        public Worker2() {
            this.aie = new AsynchronouslyInterruptedException();
            RelativeTime period = 
                    new RelativeTime(1000,0);
            ReleaseParameters rel = 
                    new PeriodicParameters(period);
            this.setReleaseParameters(rel);
        }

        public void run(AsynchronouslyInterruptedException aie)
                throws AsynchronouslyInterruptedException
        {
            while ( true ) {
                System.out.println("Worker2 doing work...");
                waitForNextPeriod();
            }
        }
        
        public void interruptAction(
                AsynchronouslyInterruptedException aie) {
            System.out.println("Worker2 interrupted");
        }
        
        public void run() {
            aie.doInterruptible(this);
        }
    }

    public Main() {
        Worker1 w1 = new Worker1();
        Worker2 w2 = new Worker2();
        
        w1.start();
        w2.start();
        
        try {
            RelativeTime interval = new RelativeTime(3000,0);
            RealtimeThread.sleep(interval);
        }
        catch ( Exception e ) { }
        
        w1.interrupt();
        w2.interrupt();
        
        System.out.println("Application terminating");
    }
            
    public static void main(String[] args) {
        Main app = new Main();
    }
}
