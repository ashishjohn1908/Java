package priorityinversion;
import javax.realtime.*;
public class Main extends RealtimeThread {
    static public Object lock = new Object();
    class HighPriority extends RealtimeThread {
        public HighPriority() {
            // set priority to high
            int pri = PriorityScheduler.instance().getMaxPriority() - 1;
            PriorityParameters sched =
                new PriorityParameters(pri);
            this.setSchedulingParameters(sched);
        }
        public void run() {
            System.out.println("H\t\t\tGetting Lock");
            synchronized ( lock ) {
                for ( int i = 0; i < 100; i++ ) {
                    System.out.println("H\t\t\t*");
                }
                System.out.println("L\tReleasing Lock");
            }
            System.out.println("H\t\t\tTERM");
        }
    }
    
    class MedPriority extends RealtimeThread {
        public MedPriority() {
            // set priority to mid-point
            int hi = PriorityScheduler.instance().getMaxPriority();
            int lo = PriorityScheduler.instance().getMinPriority();
            PriorityParameters sched =
                new PriorityParameters((hi-lo)/2);
            this.setSchedulingParameters(sched);
        }
        public void run() {
            for ( int i = 0; i < 100; i++ ) {
                System.out.println("M\t\t*");
            }
            System.out.println("M\t\tTERM");
        }
    }

    class LowPriority extends RealtimeThread {
        public LowPriority() {
            // set priority to lowest
            int pri = PriorityScheduler.instance().getMinPriority();
            //System.out.println("LowPriority at pri=" + pri);
            PriorityParameters sched =
                new PriorityParameters(pri);
            this.setSchedulingParameters(sched);
        }
        public void run() {
            System.out.println("L\tGetting Lock");
            synchronized ( lock ) {
                for ( int i = 0; i < 100; i++ ) {
                    System.out.println("L\t*");
                }
                System.out.println("L\tReleasing Lock");
            }
            System.out.println("L\tTERM");
        }
    }

    public Main() {
        // set priority to highest
        int pri = PriorityScheduler.instance().getMaxPriority();
        PriorityParameters sched =
            new PriorityParameters(pri);
        this.setSchedulingParameters(sched);
    }
    
    public void run() {
        LowPriority low = new LowPriority();
        MedPriority med = new MedPriority();
        HighPriority high = new HighPriority();

        low.start();
        System.out.println("Main: LowPriority STARTED");
        
        System.out.println("Main: Yielding for .5 milliseconds");
        RelativeTime abs = new RelativeTime(0,600000);
        try {this.sleep(abs);} catch( Exception e ) { }
        
        med.start();
        System.out.println("Main: MedPriority STARTED");
        
        high.start();
        System.out.println("Main: HighPriority STARTED");
    }
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
}
