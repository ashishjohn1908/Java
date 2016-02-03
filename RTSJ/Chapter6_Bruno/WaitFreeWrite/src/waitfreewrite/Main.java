package waitfreewrite;

import javax.realtime.*;
public class Main extends RealtimeThread {
    //public static ScopedMemory sm = new LTMemory(4096000); //~4MB
    public static WaitFreeWriteQueue queue = 
            new WaitFreeWriteQueue(5);

    class QProducer implements Runnable {
        public void run() {
            int times = 0;
            while ( times++ < 100 ) {
                //System.out.println("QProducer run()");
                String s = "This is msg# " + times;
                queue.write(s);
                RealtimeThread.waitForNextPeriod();
            }
            queue.force("term");
        }
    }
    
    class QConsumer implements Runnable {
        public void run() {
            System.out.println("QConsumer waiting on the queue...");
            try {
                boolean loop = true;
                while ( loop == true ) {
                    String msg = (String)queue.read();
                    System.out.println("QConsumer received: " + msg);
                    if ( msg.equalsIgnoreCase("term") )
                        loop = false;
                }
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }
    
    public Main() {
    }
    
    public void run() {
        startConsumer();
        startProducer();
    }
    
    private void startConsumer() {
        new Thread( new QConsumer() ).start();

        // Yield to give the consumer a chance to start
        try { RealtimeThread.sleep(1); } catch ( Exception e ){ }
    }
    
    private void startProducer() {
        ImmortalMemory.instance().enter(
            new Runnable() {
                public void run() {
                    PeriodicParameters rel = 
                        new PeriodicParameters(
                            new RelativeTime(1,0));
                    int pri = PriorityScheduler.instance().getMaxPriority();
                    PriorityParameters sched = 
                        new PriorityParameters(pri);
                            
                    new NoHeapRealtimeThread(
                        sched, rel,
                        null, ImmortalMemory.instance(),
                        null, new QProducer() ).start();
                }
            });
    }
    
    public static void main(String[] args) {
        //Main main = new Main();
        new Main().start();
    }

}
