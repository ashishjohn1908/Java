package waitfreereader2;
import javax.realtime.*;
public class Main extends RealtimeThread {
    public static WaitFreeReadQueue queue = 
            new WaitFreeReadQueue(5, true);
    
    class QProducer implements Runnable {
        public void run() {
            int count = 0;
            while ( count++ < 100 ) {
                System.out.println("QProducer writing message");
                safeWrite(count);
                try { Thread.sleep(10); } catch ( Exception e ) { }
            }
            safeWrite(-1);
        }

        private void safeWrite(final int count) {
            ImmortalMemory.instance().executeInArea( 
              new Runnable() {
                public void run() {
                    try {
                        if ( queue.isFull() )
                            System.out.println("QProducer blocking");
                        if ( count == -1 ) 
                            queue.write("TERM");
                        else 
                            queue.write("Msg #" + count);
                    }
                    catch ( Exception e ) { }
                }
            });
        }
    }

    class QConsumer implements Runnable {
        public void run() {
            try {
                while ( true ) {
                    System.out.println("NHRT QConsumer waiting");
                    queue.waitForData();
                    String msg = (String)queue.read();
                    System.out.println("  NHRT QConsumer received: " + msg);
                    if ( msg.equalsIgnoreCase("TERM") )
                        return;
                }
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        startProducer();
        startConsumer();
    }
    
    private void startProducer() {
        new Thread( new QProducer() ).start();
    }
    
    private void startConsumer() {
        ImmortalMemory.instance().enter(
            new Runnable() {
                public void run() {
                    int pri = PriorityScheduler.instance().getMaxPriority();
                    PriorityParameters sched = 
                        new PriorityParameters(pri);
                    new NoHeapRealtimeThread(
                        sched, null,
                        null, ImmortalMemory.instance(),
                        null, new QConsumer() ).start();
                }
            });
    }
    
    public static void main(String[] args) {
        new Main().start();
    }
}
