class QueueDouble {
    static int lastProcessed = -1;
    static int freePlace = 0;

    // Max objects is set to 1000
    static double[] container = new double[1000];

    // Stack in FIFO order
    static void add(double a) {
        if (freePlace < 1000) {
            container[freePlace] = a;
            freePlace++;
        }
    }

    // Process in FIFO order
    static double process() {
        if (freePlace - lastProcessed > 1) {
            lastProcessed++;
            return container[lastProcessed];
        } else
            return -1.0; // code for impossible to process
    }

    public static void main(String[] arg) {
        System.out.println("Queue demo:");
        add(3.0);
        add(5.0);
        add(7.0);
        System.out.println(process());
        System.out.println(process());
        System.out.println(process());
        System.out.println(process());
        System.out.println(process());
    }

}