class QueueIntGame {
    final static int n = 1000;
    static int lastProcessed = -1;
    static int freePlace = 0;
    static int[] container = new int[n];
    static boolean[] mark = new boolean[n];

    static void add(int a) {
        if (freePlace < n) {
            container[freePlace] = a;
            freePlace++;
        }
    }

    static boolean Empty() {
        return ((freePlace - lastProcessed) == 1);
    }

    static void process() {
        int a;
        lastProcessed++;
        a = container[lastProcessed];
        if (a < n) mark[a] = true;
        if (2 * a + 1 < n) add(2 * a + 1);
        if (3 * a < n) add(3 * a);
    }

    public static void main(String[] arg) {
        int i;

        for (i = 0; i < n; i++)
            mark[i] = false;

        add(1);
        while (!Empty())
            process();

        for (i = 0; i < n; i++) {
            if (mark[i])
                System.out.print(i + " ");
        }
        System.out.println("");
    }
}