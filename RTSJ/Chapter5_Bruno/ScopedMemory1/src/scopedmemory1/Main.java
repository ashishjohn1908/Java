package scopedmemory1;

import javax.realtime.*;
import java.util.*;

public class Main extends RealtimeThread {
    class MyLogic implements Runnable {
        public MyLogic() {
            System.out.println("MyLogic class created"); 
        }
        
        public void run() {
            System.out.println("MyLogic entering run()");
            System.out.println("MyLogic terminating");
        }
    }

    //static TempGauge temp;
    static ScopedMemory sm = null;
    
    public Main() {
    }

    public void run() {
        // execute TempGauge in scoped memory in current RTT
        int memSize = 4096000; // ~4MB in size
        sm = new LTMemory( memSize, new MyLogic() );
        sm.enter( /*new MyLogic()*/ );
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
}
