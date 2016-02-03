package plamen;

import net.jcip.examples.StripedMap;

import java.util.concurrent.TimeUnit;

/**
 * Created by plamen on 12/06/2014.
 */
public class ThreadOrdering {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Worker w = new Worker("A");
        Worker w1 = new Worker("B");
        Worker w2 = new Worker("C");
        Worker w3 = new Worker("D");
        Worker w4 = new Worker("E");

        w.start();
        w1.start();
        w2.start();
        w3.start();
        w4.start();

        w.join();
        w1.join();
        w2.join();
        w3.join();
        w4.join();

     //   TimeUnit.SECONDS.sleep(1);
        System.out.println(System.currentTimeMillis() - start);
    }
}



class Worker extends Thread{
    private String name;

    Worker( String name){
        super();
        this.name = name;
    }


    @Override
    public void run() {
        System.out.println("Thread :" + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}