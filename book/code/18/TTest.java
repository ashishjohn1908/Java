// Demonstrate Timer and TimerTask.

import java.util.*;

class MyTimerTask extends TimerTask {
    public void run() {
        System.out.println("Timer task executed." + '\7');
    }
}

class TTest {
    public static void main(String args[]) {
        long startTime = System.currentTimeMillis();
        MyTimerTask myTask = new MyTimerTask();
        Timer myTimer = new Timer();

        /* Set an initial delay of 1 second,
           then repeat every half second.
        */
        myTimer.schedule(myTask, 1000, 500);

        try {
            Thread.sleep(8000);
        } catch (InterruptedException exc) {
        }

        myTimer.cancel();
        Long sec = System.currentTimeMillis() - startTime;
        System.out.println(Math.round(sec.intValue()) / 1000);

    }
}