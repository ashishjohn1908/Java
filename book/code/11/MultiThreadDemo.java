class MultiThreadDemo {
    // Create multiple threads.
    static class NewThread implements Runnable {
        String name; // name of thread
        Thread t;

        NewThread(String threadname, int priority) {
            name = threadname;
            t = new Thread(this, name);
            t.setPriority(priority);
            System.out.println("New thread: " + t);
            t.start(); // Start the thread
        }

        // This is the entry point for thread.
        public void run() {
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.println(name + ": " + i);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                System.out.println(name + "Interrupted");
            }
            System.out.println(name + " exiting.");
        }
    }


    public static void main(String args[]) {
        new NewThread("One", Thread.NORM_PRIORITY); // start threads
        new NewThread("Two", Thread.MIN_PRIORITY);
        new NewThread("Three", Thread.MAX_PRIORITY);

        try {
            // wait for other threads to end
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }

        System.out.println("Main thread exiting.");
    }
}