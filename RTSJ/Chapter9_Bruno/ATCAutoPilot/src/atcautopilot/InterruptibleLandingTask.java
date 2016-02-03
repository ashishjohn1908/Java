package atcautopilot;
import javax.realtime.*;
public class InterruptibleLandingTask 
        extends RealtimeThread
        implements Interruptible
{
    static int Mode = -1;
    static public final int LANDING_MODE = 1;
    static public final int TOUCHDOWN_MODE = 2;    

    String taskName;
    AsynchronouslyInterruptedException aie;
    boolean interrupted = false;
    
    public InterruptibleLandingTask(
            String taskName,
            AsynchronouslyInterruptedException aie,
            RelativeTime period,
            int priority) {
        this.aie = aie;
        this.taskName = taskName;
        PeriodicParameters rel = 
            new PeriodicParameters(period);
        PriorityParameters pri = 
            new PriorityParameters(priority);
        setReleaseParameters(rel);
        setSchedulingParameters(pri);
    }

    public void run() {
        System.out.println("\t"+taskName+" monitor started");
        while ( Mode == LANDING_MODE  ) {
            if ( ! aie.doInterruptible(this) )
                RealtimeThread.yield();
            else if ( ! isInterrupted() )
                waitForNextPeriod();

            // For tasks that execute code with deferred
            // interruption, check it here
            if ( aie.clear() )
                interrupted = true;
        }
        System.out.println("\t"+taskName+" task terminating");
    }

    // Shoud be called via super
    public void interruptAction(AsynchronouslyInterruptedException aie) {
        interrupted = true;
        System.out.println("\t"+taskName+" task interrupted *****");
    }

    public boolean isInterrupted() {
        return interrupted;
    }
    
    // Must be overriden
    public void run(AsynchronouslyInterruptedException aie) 
      throws AsynchronouslyInterruptedException {
    }
    
    public static void setMode(int mode) {
        Mode = mode;
    }
}
