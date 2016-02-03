package timeinterval;
import javax.realtime.*;
import java.util.Date;
public class Main {
    public static void main(String[] args) {
        AbsoluteTime current = Clock.getRealtimeClock().getTime();
        Date time = current.getDate();
        System.out.println("Current date/time: " + time);
        
        // Create the objects beforehand; eliminates jitter
        AbsoluteTime before;
        AbsoluteTime after;
        RelativeTime elapsed;

        before = Clock.getRealtimeClock().getTime();
        // perform operation here...
        try { RealtimeThread.sleep(new RelativeTime(1,0)); }
          catch ( Exception e ) { }
        after = Clock.getRealtimeClock().getTime();
        
        elapsed = after.subtract(before);
        System.out.println("Elapsed time: " + elapsed);
    }
}
