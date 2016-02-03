/* Copyright 2009 Sun Microsystems, Inc.  All Rights Reserved. */

import javax.realtime.*;
import java.io.*;

public class HelloWorldRT {


    // Global scope constants
    static final int ITERATIONS = 10000;          // Number of iterations of the periodic thread
    static final int PERIOD = 2;                  // Period in milliseconds

    // A periodic real-time thread class measuring latencies in thread rescheduling times
    static class PeriodicThread extends RealtimeThread {

        Clock        refRTClock;
        RelativeTime usedPeriod;
        AbsoluteTime expectedStartTime;
        StringWriter noticeableLatencyRecordingWriter;       // A noticeable latency is at least 100 microseconds
        StringWriter highestLatencyWriter;


        PeriodicThread(Clock rtClock,
                       RelativeTime period,
                       AbsoluteTime startTime,
                       StringWriter noticeableLatencyWriter,
                       StringWriter worstLatencyWriter) {

            refRTClock = rtClock;
            usedPeriod = period;
            expectedStartTime = startTime;
            noticeableLatencyRecordingWriter = noticeableLatencyWriter;
            highestLatencyWriter = worstLatencyWriter;
        }


        // Refined RealtimeThread.run() method: for each loop, 
        // from real-time clock we collect first the activation time of the real-time thread,
        // compute the expected activation time by adding the period to previous expected time,
        // then compute the latency in activation using the difference between those two times.
        public void run() {

            AbsoluteTime expectedActivationTime =
                new AbsoluteTime(expectedStartTime);       // For the first loop,  expected activation time is start time

            AbsoluteTime activeTime = new AbsoluteTime();             // Measured rescheduling time
            RelativeTime loopLatency = new RelativeTime();            // Calculated latency of the current loop
            RelativeTime worstLatency = new RelativeTime();           // Worst latency observed so far
           
            int nanos = 0;
            long millis = 0;

            for (int i = 0; i < ITERATIONS; i++) {
                try {
                    if ( !waitForNextPeriod() ) {
                        throw new Exception(" Deadline missed at loop " + i);
                    }

                    // Records entry time in period
                    refRTClock.getTime(activeTime);

                    // Calculates expected activation time for this loop
                    expectedActivationTime.add(usedPeriod, expectedActivationTime);

                    // Calculates activation time latency for this loop
                    activeTime.subtract(expectedActivationTime, loopLatency);

                    // Records unexpected latencies (starting after first loop)
                    if (i > 0) {

                        nanos = loopLatency.getNanoseconds();
                        millis = loopLatency.getMilliseconds();

                        // If latency higher than 100 microseconds, record it 
                        if ( nanos >= 100000
                             || millis > 0) {

                            noticeableLatencyRecordingWriter.write(
                                         "\n Loop " +  (i+1) +
                                         ": Rescheduling time  = " + activeTime +
                                         "; Noticeable latency = " + loopLatency);
                        }

                        // If latency exceeds the worst case so far, record it
                        if ( millis > worstLatency.getMilliseconds()
                             || (millis == worstLatency.getMilliseconds()
                                 && nanos > worstLatency.getNanoseconds()) ) {

                            highestLatencyWriter.write("\n New highest latency at loop " + (i+1) + " = " + loopLatency);
                            worstLatency.set(loopLatency);
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    // Even if we get a deadline miss, we still need to
                    // update the expected activation time for next loop computation
                    expectedActivationTime.add(usedPeriod, expectedActivationTime);

                }
            }

            System.out.println("Real-time thread execution finished.\n");
        }
    }
	
    public static void main(String[] args) {

	System.out.println("\nHello Real-Time World !");

        // Reminder of basic program parameters 
        System.out.println("----------------------------------");
        System.out.println(" Number of iterations = " + ITERATIONS);
        System.out.println(" Period value = "  + PERIOD + " milliseconds (ms)");
        System.out.println("----------------------------------");


        // Utility string writers to record and print out latencies,
        // writing to memory and avoiding standard output I/O while in RealTimeThread run()
        StringWriter noticeableLatencyWriter = new StringWriter(5000);
        StringWriter worstLatencyWriter      = new StringWriter(5000);

        // Initializes real-time program variables
        PriorityScheduler ps   = (PriorityScheduler) Scheduler.getDefaultScheduler();
        int maxPriority        = ps.getMaxPriority();
        Clock rtClock          = Clock.getRealtimeClock();
        RelativeTime period    = new RelativeTime(PERIOD, 0);
        AbsoluteTime startTime = rtClock.getTime().add(1000,0);

        // Initializes and starts the periodic RealTimeThread
        PeriodicThread periodicThread =
            new PeriodicThread(rtClock, period, startTime, noticeableLatencyWriter, worstLatencyWriter);
        periodicThread.setPriority(maxPriority);
        periodicThread.setReleaseParameters(new PeriodicParameters(startTime, period));
	periodicThread.start();

        // Waits for RealTime thread and reports results on standard output
	try {
  	    periodicThread.join();
            System.out.print("Noticeable latencies (values >= 100 microseconds) in order of occurrence (if any): ");
            System.out.println(noticeableLatencyWriter);
            System.out.print("\nHighest latency values in increasing order: ");
            System.out.println(worstLatencyWriter);
            
            
            // All done
            System.out.println("\n\nBye, Real-Time World ..."); 

	} catch(InterruptedException e) {
	    throw new Error("Impossible interrupt!");
	}
    }
}






