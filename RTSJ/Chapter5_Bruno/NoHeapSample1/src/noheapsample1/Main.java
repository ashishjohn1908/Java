package noheapsample1;

import javax.realtime.*;

public class Main extends RealtimeThread {
    public Main() {
        System.out.println("In Main constructor");
    }

    public void run() {
        System.out.println("in Main run()");
        
        // Set immortal memory as allocation context
        ImmortalMemory.instance().enter(this);
        System.out.println("In IM context");

        // Create NHRT (within immortal memory)
        NoHeapRealtimeThread nhrt = 
            new NoHeapRealtimeThread(
                null, ImmortalMemory.instance()) 
        {
            public void run() {
                // Execute NHRT code here...
                System.out.println("I'm in an NHRT!");
            }
        };
        
        nhrt.start();
    }

    public static void main(String[] args) throws Exception {
        // Create and start a RealtimeThread object
        System.out.println("Creating Main object");
        Main app = new Main();
        System.out.println("Starting Main");
        app.start();
        app.join();
    }    
}

