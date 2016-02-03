package schedulable1;

import javax.realtime.*;

public class Main {

    class MySched extends RealtimeThread
    {
        public MySched()
        {
            System.out.println("RealtimeThread " + this.getId()+ " created");
        }
        
        public void run()
        {
            int times = 0;
            while ( times++ < 50 ) {
                System.out.println("RealtimeThread " + this.getId() + " Released");

                waitForNextPeriod();
            }
        }
    }
    
    public Main()
    {
        PriorityParameters HiPri = new PriorityParameters(PriorityScheduler.instance().getNormPriority()+10);

        RealtimeThread rt = new RealtimeThread(HiPri) {
            public void run() {
                    MySched rt1 = new MySched();
                    MySched rt2 = new MySched();
                    MySched rt3 = new MySched();
                    RelativeTime milli = new RelativeTime(1,0);
                    ReleaseParameters p = new PeriodicParameters(milli);

                    rt1.setReleaseParameters(p);
                    rt2.setReleaseParameters(p);
                    rt3.setReleaseParameters(p);

                    RelativeTime deadline = milli;

                    // Set the deadline for all 3 Schedulable objects
                    p.setDeadline(deadline);
                    System.out.println("Deadline set to: " + deadline.toString());

                    // The following has no affect on scheduling
                    deadline = new RelativeTime(2,0);
                    
                    MySched sched = new MySched();
                    // ...
                    
                    ReleaseParameters r = sched.getReleaseParameters();
                    RelativeTime d = r.getDeadline();
                    d.toString();
                }

                //...

                //System.exit(0);
            
        };
        
        rt.start();
    }

    public static void main(String[] args) {
        Main m = new Main();
    }

}
