package atcautopilot;
import javax.realtime.*;
import java.util.*;
public class Main extends RealtimeThread {
    private int Mode;
    static public final int LANDING_MODE = 1;
    static public final int TOUCHDOWN_MODE = 2;    

    static public final int NORM_PRI =
        PriorityScheduler.instance().getMinPriority();

    class LevelFlight extends InterruptibleLandingTask {
        public LevelFlight(AsynchronouslyInterruptedException aie) {
            super( "LevelFlight", 
                   aie, 
                   new RelativeTime(100,0),
                   PriorityScheduler.instance().getMinPriority() );
            start();
        }

        public void run(AsynchronouslyInterruptedException aie) 
          throws AsynchronouslyInterruptedException {
            checkAndLevelWings(); // not interruptible
        }
        
        private void checkAndLevelWings() {
            Random rand = new Random(System.currentTimeMillis());
            boolean notLevel = (rand.nextInt(10) > 5);
            if ( notLevel ) {
                System.out.print("\tWings not level");
                if ( rand.nextInt(2) > 0 )
                    System.out.println("--Left aileron up");
                else
                    System.out.println("--Right aileron up");
            }
        }

        public void interruptAction(AsynchronouslyInterruptedException aie) {
            super.interruptAction(aie); // should never happen
        }
    }

    class Airspeed extends InterruptibleLandingTask {
        boolean reverseThrustOn = false;
        public Airspeed(AsynchronouslyInterruptedException aie) {
            super( "Airspeed", 
                   aie, 
                   new RelativeTime(1000,0),
                   PriorityScheduler.instance().getMinPriority() );
            start();
        }

        public void run() {
            super.run(); // won't return until touchdown
            reverseThrust();
        }

        public void run(AsynchronouslyInterruptedException aie) 
          throws AsynchronouslyInterruptedException {
            System.out.println("\tChecking airspeed");
            RelativeTime slow = new RelativeTime(250,0);
            try { RealtimeThread.sleep(slow); } catch ( Exception e ) { }
        }
        
        public void interruptAction(AsynchronouslyInterruptedException aie) {
            super.interruptAction(aie);
            reverseThrust();
        }
        
        private void reverseThrust() {
            if ( ! reverseThrustOn ) {
                System.out.println("\tAirspeed: reverse thrust");
                reverseThrustOn = true;
            }
        }
    }

    class Altitude extends InterruptibleLandingTask {
        public Altitude(AsynchronouslyInterruptedException aie) {
            super( "Altitude", 
                   aie, 
                   new RelativeTime(1000,0),
                   PriorityScheduler.instance().getMinPriority() );
            start();
        }

        // Interruptible.run
        public void run(AsynchronouslyInterruptedException aie) 
          throws AsynchronouslyInterruptedException {
            System.out.println("\tChecking altitude");
            RelativeTime slow = new RelativeTime(250,0);
            try { RealtimeThread.sleep(slow); } catch ( Exception e ) { }
        }
        
        public void interruptAction(AsynchronouslyInterruptedException aie) {
            super.interrupted();
        }
    }

    class AngleOfAttack extends InterruptibleLandingTask {
        public AngleOfAttack(AsynchronouslyInterruptedException aie) {
            super( "AngleOfAttack", 
                   aie, 
                   new RelativeTime(100,0),
                   PriorityScheduler.instance().getMinPriority() );
            start();
        }

        public void run() {
            super.run(); // won't return until touchdown
            noseDown();
        }
        
        // Interruptible.run
        public void run(AsynchronouslyInterruptedException aie) 
          throws AsynchronouslyInterruptedException {
            Random rand = new Random(System.currentTimeMillis());
            boolean correct = (rand.nextInt(10) > 5);
            if ( ! correct ) {
                System.out.print("\tAdjusting angle of attack");
                if ( rand.nextInt(2) > 0 )
                    System.out.println(" --Horizontal stabalizer up");
                else
                    System.out.println(" --Horizontal stabalizer down");
            }
        }
        
        public void interruptAction(AsynchronouslyInterruptedException aie) {
            super.interrupted();
            noseDown();
        }
        
        private void noseDown() {
            System.out.println("\tAngleOfAttack: nose down full");
        }
    }

    class LandingGearSensor extends AsyncEventHandler {
        AsynchronouslyInterruptedException aie;
        public LandingGearSensor(AsynchronouslyInterruptedException aie) {
            this.aie = aie;
        }
        public void handleAsyncEvent() {
            System.out.println("Landing gear sensor: beginning touchdown sequence...");
            InterruptibleLandingTask.setMode(TOUCHDOWN_MODE);
            aie.fire();
        }
    }

    public Main() {
        PriorityParameters priority =
          new PriorityParameters(
                PriorityScheduler.instance().getMaxPriority());
        setSchedulingParameters(priority);
        this.start();
    }
    
    public int getMode() { return Mode; }
    
    public void run() {
        try {
            // Start the system flying, but in landing mode
            System.out.println("Beginning landing sequence...");
            InterruptibleLandingTask.setMode(LANDING_MODE);

            AsynchronouslyInterruptedException aie = 
                new AsynchronouslyInterruptedException();
            Airspeed speed = new Airspeed(aie);
            Altitude alt = new Altitude(aie);
            LevelFlight level = new LevelFlight(aie);
            AngleOfAttack attack = new AngleOfAttack(aie);
            
            // Create the landing gear event 
            LandingGearSensor sensor = 
                    new LandingGearSensor(aie);
            AsyncEvent landingGear = new AsyncEvent();
            landingGear.setHandler(sensor);
            
            // Wait some time and transition to touchdown mode
            RealtimeThread.sleep(5000, 0);
            landingGear.fire();
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
    }
}
