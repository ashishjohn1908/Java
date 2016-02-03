/* An object may be observed by two or more
   observers.
*/

import java.util.Observable;
import java.util.Observer;

class TwoObservers {
    // This is the first observing class.
    static class Watcher1 implements Observer {
        public void update(Observable obj, Object arg) {
            System.out.println("update() called, count is " + ((Integer) arg).intValue());
        }
    }

    // This is the second observing class.
    static class Watcher2 implements Observer {
        public void update(Observable obj, Object arg) {
            // Ring bell when done
            if (((Integer) arg).intValue() == 0)
                System.out.println("Done" + '\7');
        }
    }

    // This is the class being observed.
    static class BeingWatched extends Observable {
        void counter(int period) {
            for (; period >= 0; period--) {
                setChanged();
                notifyObservers(new Integer(period));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Sleep interrupted");
                }
            }
        }
    }


    public static void main(String args[]) {
        BeingWatched observed = new BeingWatched();
        Watcher1 observing1 = new Watcher1();
        Watcher2 observing2 = new Watcher2();

        // add both observers
        observed.addObserver(observing2);
        observed.addObserver(observing1);


        observed.counter(20);
    }
}