package plamen;

import net.jcip.examples.IndexingService;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by plamen on 27/09/2014.
 */
public class ProdConsThreading {

    static class SharedObject{
        private static Deque<Integer> s = new ConcurrentLinkedDeque<>();


        public static Integer get() {

            return s.getLast();
        }

        public static void set(Integer n) {
            s.addLast(n);
        }
    }

    class Producer implements Runnable{
        final Thread t;
        Producer() {
            t = new Thread(this, "Producer");
            t.start();
        }

        @Override
        public void run() {
            for(int i = 0; i < 20; i++){
                SharedObject.set(i);
                System.out.printf("%s put: %d\n", t.getName(), i);
             /*
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              */
            }
        }
    }

    class Consumer implements Runnable{
        final Thread t;
        Consumer(String name) {
            t = new Thread(this, name);
            t.start();
        }

        @Override
        public void run() {
            for(int i = 0; i < 20; i++){
               /*
                try {
                    TimeUnit.MILLISECONDS.sleep(999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               */
                System.out.printf("%s get: %d\n,", t.getName(), SharedObject.get());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProdConsThreading pct = new ProdConsThreading();
        Producer p = pct.new Producer();
        TimeUnit.MILLISECONDS.sleep(1);
        Consumer c = pct.new Consumer("Consumer1");
        Consumer c1 = pct.new Consumer("Consumer2");

        p.t.join();
        c.t.join();
        c1.t.join();
    }
}
