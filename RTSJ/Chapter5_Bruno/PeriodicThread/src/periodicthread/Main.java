/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package periodicthread;

import javax.realtime.*;
import java.util.Random;

public class Main {

    class TempGauge extends RealtimeThread
    {
        static final int LOW_TEMP = 25;
        static final int HIGH_TEMP = 75;
        
        public TempGauge()
        {
            System.out.println("TempGauge created with thread ID: " + 
                    this.getId() ); 
        }
        
        public void run()
        {
            System.out.println("TempGauge entering run()");

            int times = 0;
            while ( times++ < 1000 ) {
                int currentTemp = getReactorTemp();
                System.out.println("Temp = " + currentTemp);

                if ( currentTemp <= LOW_TEMP )
                    System.out.println("The reactor temperature is too low!!");
                else if ( currentTemp >= HIGH_TEMP)
                    System.out.println("The reactor temperature is too high!!");

                boolean ok = waitForNextPeriod();
            }
            System.out.println("TempGauge exiting with times=" + times);
        }
        
        private int getReactorTemp()
        {
            // Just pretend, and generate a random temp :)
            Random r = new Random( System.currentTimeMillis() );
            return r.nextInt(100); // random between 0 and 100
        }
    }
    
    class CostOverrunHandler extends AsyncEventHandler {
        public CostOverrunHandler() {
        }
        public void handleAsyncEvent() {
            System.out.println("!!!!!!!!!!!!!!!! Cost overrun has occured");
        }
    }

    class DeadlineMissHandler extends AsyncEventHandler {
        private Schedulable sched = null;
        public DeadlineMissHandler(Schedulable sched) {
            super(new PriorityParameters(
                    PriorityScheduler.instance().getMaxPriority()),
                    null, null, null, null, null);
            this.sched = sched;
        }
        public void handleAsyncEvent() {
            System.out.println("&&&&&&&&&&&&&&&& Deadline miss has occured");
            if ( sched instanceof RealtimeThread)
                ((RealtimeThread)sched).schedulePeriodic();
        }
    }

    public Main()
    {
        // Create the Schedulable (a temperature gauge)
        System.out.println("Creating TempGauge");
        TempGauge sched = new TempGauge();

        // Set SchedulingParameters for priority
        int normPri = PriorityScheduler.instance().getNormPriority();
        PriorityParameters pri = new PriorityParameters(normPri);
        //sched.setSchedulingParameters(pri);
        
        // Set a one-millisecond period
        RelativeTime period = new RelativeTime(1,0); // 1 milli
        RelativeTime delay = new RelativeTime(1,0); // 1 second
        RelativeTime cost = new RelativeTime(0,1); // 1 nano
        RelativeTime deadline = new RelativeTime(0,1000); // 1 milli
        CostOverrunHandler coh = new CostOverrunHandler();
        DeadlineMissHandler dmh = new DeadlineMissHandler(sched);
        PeriodicParameters per = 
            new PeriodicParameters( null, period,  
                                    null, deadline, 
                                    null, dmh);

        sched.setReleaseParameters(per);

        sched.start();
        System.out.println("TempGauge Thread Started");
    }

    public static void main(String[] args) {
        Main m = new Main();
    }

}
