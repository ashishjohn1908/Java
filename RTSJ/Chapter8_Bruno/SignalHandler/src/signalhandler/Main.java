package signalhandler;
import javax.realtime.*;
public class Main {
    SigintHandler handler = null;
    final int RTSJ_MAX_PRI = 
        PriorityScheduler.instance().getMaxPriority();
    
    class SigintHandler extends AsyncEventHandler {
        public SigintHandler() {
            setSchedulingParameters(
                new PriorityParameters(RTSJ_MAX_PRI));
        }

        public void handleAsynchEvent() {
            System.out.println("SIGINT occurred");
        }
    }
    
    public Main() {
        handler = new SigintHandler();
        POSIXSignalHandler.addHandler(
                POSIXSignalHandler.SIGINT, handler);
    }
    public static void main(String[] args) {
        Main app = new Main();
        while ( true ) {
            try { Thread.sleep(10); } catch ( Exception e ) { }
        }
    }
}
