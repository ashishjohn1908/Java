package noheaphelper;

import java.util.*;
import javax.realtime.*;

public class TempGauge implements Runnable {
    static final int LOW_TEMP = 25;
    static final int HIGH_TEMP = 75;
    static final Random r = 
            new Random( System.currentTimeMillis() );
    
    public TempGauge()
    {
        System.out.println("TempGauge object created"); 
    }

    public void run()
    {
        System.out.println("TempGauge entering run()");

        int times = 0;
        while ( times++ < 1 ) {
            int currentTemp = getReactorTemp();
            System.out.println("TempGuage(" + this + "): " + currentTemp);

            if ( currentTemp <= LOW_TEMP )
                System.out.println("The reactor temperature is too low!!");
            else if ( currentTemp >= HIGH_TEMP)
                System.out.println("The reactor temperature is too high!!");

            boolean ok = NoHeapRealtimeThread.waitForNextPeriod();
            if ( ! ok )
                System.out.println("TempGauge deadline miss");
        }

        System.out.println("TempGauge terminating");
    }

    private int getReactorTemp()
    {
        // Just pretend, and generate a random temp :)
        //return r.nextInt(100); // random between 0 and 100
        return 50;
    }

}
