package simplertt;

import javax.realtime.*;

public class Main {

    class MyRunnable implements Runnable {
        public void run() {
            System.out.println("MyRunnable.run()");
            Exception e1 = new Exception();
            e1.printStackTrace();
        }
    }
    
    public Main() {
        MyRunnable rnbl = new MyRunnable();
        RealtimeThread rtt = new RealtimeThread(null,null,null,null,null,rnbl);
        rtt.start();
    }
    
    public static void main(String[] args) {
        Main m = new Main();
    }

}
