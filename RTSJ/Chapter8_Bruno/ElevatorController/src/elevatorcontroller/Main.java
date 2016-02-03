package elevatorcontroller;

import javax.realtime.*;
public class Main {
    final static int TEN_SECONDS = 10000;
    Clock rtClock = Clock.getRealtimeClock();
    
    class FloorSensor extends AsyncEventHandler {
        public void handleAsyncEvent() {
            System.out.println("FloorSensor event");
            stopElevator();
            openDoors();
        }
    }
    
    class DoorTimer extends AsyncEventHandler {
        public void handleAsyncEvent() {
            // Time has expired - close doors
            System.out.println("DoorTimer expired");
            closeDoors();
        }
    }

    public void stopElevator() {
        System.out.println("\tStopping elevator");
        // ...
    }

    public void openDoors() {
        System.out.println(
                "\tOpening doors at time=" + 
                rtClock.getTime().getDate());
        // ...

        // start time to close doors
        OneShotTimer doorTimer = 
            new OneShotTimer( 
              new RelativeTime( TEN_SECONDS, 0 ), 
              new DoorTimer() );
        doorTimer.enable();
        doorTimer.start();
    }
    
    public void closeDoors() {
        System.out.println(
                "\tClosing doors at time=" +
                rtClock.getTime().getDate());
        // ...
    }
    
    public Main() {
        FloorSensor handler = new FloorSensor();
        AsyncEvent floorEvent = new AsyncEvent();
        floorEvent.setHandler(handler);
        System.out.println("Elevator moving...");
        
        // wait some time to simulate elevator motion
        // and then fire floor event
        try { RealtimeThread.sleep(1000,0); } catch (Exception e) { }
        floorEvent.fire();
        try { RealtimeThread.sleep(30000,0); } catch (Exception e) { }
    }

    public static void main(String[] args) {
        Main app = new Main();
    }
}
