// An incorrect implementation of a producer and consumer.

class PC {
    static class Q {
        int n;
        static int c = 0;

        synchronized int get() {
            System.out.println("Got: " + n);
            System.out.println(c);
            return n;
        }

        synchronized void put(int n) {
            this.n = n;
            System.out.println("Put: " + n);
        }
    }

    static class Producer implements Runnable {
        Q q;

        Producer(Q q) {
            this.q = q;
            new Thread(this, "Producer").start();
        }

        public void run() {
            int i = 0;

            while (true) {
                q.put(i++);
            }
        }
    }

    static class Consumer implements Runnable {
        Q q;

        Consumer(Q q) {
            this.q = q;
            new Thread(this, "Consumer").start();
        }

        public void run() {
            while (true) {
                q.get();
            }
        }
    }


    public static void main(String args[]) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);

        System.out.println("Press Control-C to stop.");
    }
}