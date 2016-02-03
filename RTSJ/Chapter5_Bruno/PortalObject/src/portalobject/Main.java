package portalobject;
import javax.realtime.*;
public class Main extends RealtimeThread {
    static ScopedMemory sm = new LTMemory(4096000);//~4MB
    public Main(MemoryArea mem) {
        super(null, null, null, mem, null, null);
    }
    
    class Worker implements Runnable {
        public void run() {
            synchronized (sm) {
                long id = RealtimeThread.currentRealtimeThread().getId();
                String msg = (String)sm.getPortal();
                if ( msg == null ){
                    msg = "Thread " + id +
                          " is the portal controller";
                    sm.setPortal(msg);
                    System.out.println("Thread " + id + ": I am controller");
                }
                else {
                    System.out.println("Thread " + id + ": " + msg);
                }
            }
        }
    }
    
    public void run () {
        for ( int i = 0; i < 3; i++ ) {
            Worker w = new Worker();
            RealtimeThread r = 
              new RealtimeThread(null, null, null, sm, null, w);
            r.start();
        }
    }
    
    public static void main(String[] args) {
        Main app = new Main(sm);
        app.start();
    }
}
