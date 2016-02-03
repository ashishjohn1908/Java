class Namerunnable implements Runnable {

    @Override
    public void run() {

        try {
            for (int i = 3; i > 0; i--) {
                Thread.sleep(500);
                System.out.println("NameRunnable running " + Thread.currentThread().getName());

            }
            System.out.println("");
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}

public class NameThread {
    private static Thread t;

    public static void main(String[] args) throws InterruptedException {
        Namerunnable namerunnable = new Namerunnable();

        runThreads(namerunnable);

        Thread.sleep(1000);
    }

    private static void runThreads(Namerunnable namerunnable) {
        t = new Thread(namerunnable);
        t.setName("Fred");
        t.setPriority(Thread.MAX_PRIORITY);
        System.out.println("Thread.MAX_PRIORITY: " + Thread.MAX_PRIORITY);
        System.out.println("Thread.NORM_PRIORITY: " + Thread.NORM_PRIORITY);
        System.out.println("Thread.MIN_PRIORITY: " + Thread.MIN_PRIORITY);
        t.start();

        t = new Thread(namerunnable);
        t.setName("Chain");
        t.setPriority(Thread.NORM_PRIORITY);
        t.start();

        t = new Thread(namerunnable);
        t.setName("Pino");
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }
}
