package timebasedrtgc;

import javax.realtime.*;
import com.sun.rtsjx.*;
import javax.xml.*;

public class Main 
{
    class TimeBasedRTGC implements Runnable
    {
        final int RTSJ_MAX_PRI = PriorityScheduler.instance().getMaxPriority();
        
        class PriorityHandler extends AsyncEventHandler {
            public PriorityHandler() {
                setSchedulingParameters(new PriorityParameters(RTSJ_MAX_PRI));
            }
            public void handleAsyncEvent() {
                int prio = FullyConcurrentGarbageCollector.getCurrentPriority();
                System.out.println("RTGC priority="+prio);
            }
        }
        
        RealtimeThread rtt = null;
        
        volatile FullyConcurrentGarbageCollector rtgc;
        
        int start_millis, duration_millis;
        int normalPri, boostedPri;
        
        // This controller class accepts as input two millisecond
        // values. The first, start_millis, indicates how often the 
        // RTGC should start collecting. The second, duration_millis,
        // indicates how long the RTGC should run at high priority.
        // As a result, the duration must be less than the period (start).
        public TimeBasedRTGC(int start_millis, int duration_millis)
                throws Exception
        {
            System.out.println("In TimeBasedRTGC constructor");

            if ( duration_millis >= start_millis)
                throw new Exception("duration must be less than GC period");

            // Make sure the VM was started with the RTGC, not Serial GC
            GarbageCollector gc = RealtimeSystem.currentGC();
            if (! (gc instanceof FullyConcurrentGarbageCollector) ) {
		System.out.println("Must run with RTGC");
		System.exit(1);
	    }            
            rtgc = (FullyConcurrentGarbageCollector)gc;
            System.out.println("RTGC enabled");

            this.start_millis = start_millis;
            this.duration_millis = duration_millis;
	    normalPri = FullyConcurrentGarbageCollector.getNormalPriority();
            boostedPri = FullyConcurrentGarbageCollector.getCriticalBoundary();
            
            PriorityParameters priority = 
                    new PriorityParameters(RTSJ_MAX_PRI);
            PeriodicParameters period  = 
                    new PeriodicParameters(new RelativeTime(start_millis,0));

            // add a trace to see GC priority changes
            PriorityHandler ph = new PriorityHandler();
	    FullyConcurrentGarbageCollector.addPriorityHandler(ph);
            
            // Create an RTT with the right priority and schedule
            System.out.println("Starting realtime thread");
            rtt = new RealtimeThread( 
                        priority, period, null, null, null, this );
            rtt.start();
        }
        
        private void boostRTGC()
        {
            FullyConcurrentGarbageCollector.set("NormalPriority", boostedPri);
        }

        private void unBoostRTGC()
        {
            FullyConcurrentGarbageCollector.set("NormalPriority", normalPri);
        }
        
        public void run()
        {
            int pri;
            System.out.println("GC controller thread running");
            synchronized ( this ) {                
                while (true)
                {
                    String s = "Starting RTGC" + " at time " + javax.realtime.Clock.getRealtimeClock().getTime();

                    // Start a new RTGC cycle and boost the priority
                    FullyConcurrentGarbageCollector.startAsyncGC(normalPri);

                    // Boost the RTGC priority 
                    boostRTGC();

                    // Start a timer for the specified duration
                    try {
                        RelativeTime duration = 
                            new RelativeTime(duration_millis, 0);
                        HighResolutionTime.waitForObject(this, duration);
                    } catch (InterruptedException e) { }

                    // Set the RTGC priority back to normal
                    unBoostRTGC();

                    // Wait for the next start time
                    RealtimeThread.waitForNextPeriod();
                }
            }
        }
    }

    static Main.TimeBasedRTGC gcController = null;
    
    public Main() throws Exception
    {
        System.out.println("Creating GC controller");
        
        // Start time-based RTGC to run for 1 millisecond of every 10
        Main.gcController = new Main.TimeBasedRTGC(2,1);
        
        // application code here...
    }
    
    public static void main(String[] args) throws Exception 
    {
        Main main = new Main();
    }

}
