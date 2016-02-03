package ch28;

// An example of CyclicBarrier.

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class BarDemo {
    public static void main(String args[]) {
        CyclicBarrier cb = new CyclicBarrier(3, new BarAction());

        System.out.println("Starting");

        new MyThread1(cb, "A");
        new MyThread1(cb, "B");
        new MyThread1(cb, "C");

    }
}

// A thread of execution that uses a CyclicBarrier.
class MyThread1 implements Runnable {
    CyclicBarrier cbar;
    String name;

    MyThread1(CyclicBarrier c, String n) {
        cbar = c;
        name = n;
        new Thread(this).start();
    }

    public void run() {

        System.out.println(name);

        try {
            cbar.await();
        } catch (BrokenBarrierException exc) {
            System.out.println(exc);
        } catch (InterruptedException exc) {
            System.out.println(exc);
        }
    }
}

// An object of this class is called when the
// CyclicBarrier ends.
class BarAction implements Runnable {
    public void run() {
        System.out.println("Barrier Reached!");
    }
}
