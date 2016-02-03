package scopedrunloop;

import javax.realtime.*;
public class Main extends RealtimeThread {
    
    public Main() {
        ReleaseParameters rel = 
          new PeriodicParameters(
            new RelativeTime(1,0 )); // 1 milli
        
        this.setReleaseParameters(rel);
    }

    class MyLogic implements Runnable {
        public void run() {
            MemoryArea mem = RealtimeThread.getCurrentMemoryArea();
            System.out.println("Running logic in " + mem.toString());
            // ...
        }
    }
    
    static ScopedMemory sm = new LTMemory(4096000); // ~4MB
    
    public void run() {
        MyLogic logic = new MyLogic();
        
        int times = 0;
        while ( ++times < 10 ) {
            sm.enter(logic);
            waitForNextPeriod();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

}
