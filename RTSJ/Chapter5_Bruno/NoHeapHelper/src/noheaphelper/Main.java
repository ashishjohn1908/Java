package noheaphelper;

import javax.realtime.*;

public class Main {
    static ScopedMemory sm = new LTMemory(8192000);

    public static void main(String[] args) throws Exception {
        NoHeapHelper nhrtHelper =
                new NoHeapHelper(TempGauge.class);
        
        PriorityParameters pri = 
            new PriorityParameters( 
                PriorityScheduler.instance().getMaxPriority() );

        PeriodicParameters ONE_MILLI = 
            new PeriodicParameters( 
                new RelativeTime(1,0) ); 

        nhrtHelper.setSchedulingParams(pri);
        nhrtHelper.setReleaseParams(ONE_MILLI);
        
        MemoryArea mem = nhrtHelper.getMemoryArea();
        System.out.println("nhrtHelper resides in " + mem.toString());
        
        nhrtHelper.start();
        
        System.out.println("############################" );

        nhrtHelper.start();
/*
        NoHeapHelper nhrtHelper2 =
                new NoHeapHelper(TempGauge.class);
        
        PriorityParameters pri2 = 
            new PriorityParameters( 
                PriorityScheduler.instance().getMaxPriority() );

        PeriodicParameters ONE_SEC = 
            new PeriodicParameters( 
                new RelativeTime(500,0) ); 

        nhrtHelper2.setSchedulingParams(pri2);
        nhrtHelper2.setReleaseParams(ONE_SEC);
        nhrtHelper2.start();
 */ 
    }
}
