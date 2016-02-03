package clockresolution;
import javax.realtime.*;
public class Main {
    public static void main(String[] args) {
        RelativeTime t = 
            Clock.getRealtimeClock().getResolution();
        System.out.println("Real-time clock resolution = " + t.toString());
    }
}
