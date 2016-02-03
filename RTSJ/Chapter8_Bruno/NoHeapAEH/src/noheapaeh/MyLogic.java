package noheapaeh;
import javax.realtime.*;

public class MyLogic implements Runnable {
    class SomeEventHandler extends AsyncEventHandler {
        public SomeEventHandler(boolean noheap) {
            super(noheap);
        }
        public void handleAsyncEvent() {
            MemoryArea mem = 
                RealtimeThread.getCurrentMemoryArea();
            System.out.println("Event received in area " + mem);
        }
    }

    AsyncEvent event = null;
    SomeEventHandler handler = null;

    public MyLogic() {
        event =  new AsyncEvent();
        handler = new SomeEventHandler(true);
        event.addHandler(handler);
    }

    public void run() {
        event.fire();
    }
}
