package aehautopilot;

import javax.realtime.*;
public class Main {
    class AutopilotButton extends AsyncEvent {
        private boolean on = false;

        public AutopilotButton() {
            this.bindTo("DummyHappening1");
        }

        public boolean isOn() {
            return on;
        }
        
        public void fire() {
            on = !on;
            super.fire();
        }
    }

    class AutopilotHandler extends AsyncEventHandler {
        AutopilotButton button = null;

        public AutopilotHandler(AutopilotButton button) {
            this.button = button;
            setSchedulingParameters(
              new PriorityParameters(
                PriorityScheduler.instance().getMaxPriority()));
            this.button.addHandler(this);
        }

        public void handleAsyncEvent() {
            boolean state = button.isOn();
            if ( state == true )
                System.out.println("Autopilot turned on");
            else
                System.out.println("Autopilot turned off");

            // ...
        }
    }

    class Pilot extends RealtimeThread {
        AutopilotButton button = null;
        
        public Pilot(AutopilotButton button) {
            this.button = button;
            setReleaseParameters(
                new PeriodicParameters(
                    new RelativeTime(1000,0)));
        }

        public void run() {
            while ( true ) {
                // Toggle the autopilot button
                button.fire();
                waitForNextPeriod();
            }
        }
    }
    
    public Main() {
        AutopilotButton button = new AutopilotButton();
        AutopilotHandler handler = new AutopilotHandler(button);
        Pilot pilot = new Pilot(button);
        pilot.start();
    }

    public static void main(String[] args) {
        Main app = new Main();
    }
}
