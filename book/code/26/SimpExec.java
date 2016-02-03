//listing 6
// A simple example that uses an Executor. 

import java.util.concurrent.*;

class SimpExec {
    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        CountDownLatch cdl1 = new CountDownLatch(500000);
        CountDownLatch cdl2 = new CountDownLatch(500000);
        CountDownLatch cdl3 = new CountDownLatch(500000);
        CountDownLatch cdl4 = new CountDownLatch(500000);
        ExecutorService es = Executors.newFixedThreadPool(4);
        //ExecutorService es = Executors.newCachedThreadPool();

        System.out.println("Starting");

        // Start the threads.
        es.execute(new MyThread(cdl1, "A"));
        es.execute(new MyThread(cdl2, "B"));
        es.execute(new MyThread(cdl3, "C"));
        es.execute(new MyThread(cdl4, "D"));

        try {
            cdl1.await();
            cdl2.await();
            cdl3.await();
            cdl4.await();
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }

        if (!es.isTerminated())
            es.shutdown();

        System.out.println("Done");
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}

class MyThread implements Runnable {
    String name;
    CountDownLatch latch;

    MyThread(CountDownLatch c, String n) {
        latch = c;
        name = n;

        new Thread(this);
    }

    public void run() {

        for (int i = 0; i < 500000; i++) {
            System.out.println(name + ": " + i);
            latch.countDown();
        }
    }
}
