package scopedaeh;
import javax.realtime.*;
public class Main extends RealtimeThread {
    static ScopedMemory sm = new LTMemory(4096000);//4MB;

    class MyLogic implements Runnable {
        AsyncEvent event;
        public MyLogic(AsyncEvent event){
            this.event =event;
        }
        class SomeEventHandler extends AsyncEventHandler {
            public SomeEventHandler(boolean noheap) {
                super(noheap);
            }

            public void handleAsyncEvent() {
                System.out.println("MyEvent received in area " + 
                        RealtimeThread.getCurrentMemoryArea());
                try {
                    // ERROR!!
                    String s = 
                        (String)HeapMemory.instance().
                            newInstance(String.class);
                }
                catch ( Exception e ) {
                    e.printStackTrace();
                }
            }
        }

        public void run() {
            SomeEventHandler handler = new SomeEventHandler(true);
            event.addHandler(handler);
        }
    }
    
    public void run() {
        AsyncEvent event = new AsyncEvent();
        MyLogic logic = new MyLogic(event);
        ImmortalMemory.instance().executeInArea(logic);
        event.fire();
        //sm = new LTMemory(4096000);//4MB
        //sm.enter(new MyLogic(event));
    }
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }
}
