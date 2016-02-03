package noheapsample2;

import javax.realtime.*;

public class Main {
    static {
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
    
    public static void main(String[] args) {
    }
}
