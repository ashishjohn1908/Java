package waitfreewrite;

import javax.realtime.*;
public class QueueConsumer implements Runnable {
    WaitFreeWriteQueue queue = null;
    
    public QueueConsumer(WaitFreeWriteQueue queue) {
        this.queue = queue;
    }
    
    public void run() {
        System.out.println("QueueConsumer waiting on the queue...");
        try {
            boolean loop = true;
            while ( loop == true ) {
                String msg = (String)queue.read();
                System.out.println("QueueConsumer received: " + msg);
                if ( msg.equalsIgnoreCase("term") )
                    loop = false;
            }
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
