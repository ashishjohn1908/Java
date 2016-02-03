public class MysteriousProgram {
    public static void display(int[] tab) {
        for (int i = 0; i < tab.length; i++)
            System.out.print(tab[i] + " ");
        System.out.println();
    }

    public static void swap2(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }

    public static void swap3(int[] tab, int i, int j) {
        int tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }

    public static void mysterious(int[] tab, int k) {
        for (int j = k; j < tab.length; j++) {
            swap3(tab, k, j);
            display(tab);
            swap3(tab, k, j);
        }
    }

    public static void mysteriousRecursive(int[] tab, int k) {
        if (k == tab.length - 1)
            display(tab);
        for (int j = k; j < tab.length; j++) {
            swap3(tab, k, j);
            mysteriousRecursive(tab, k + 1);
            swap3(tab, k, j);
        }
    }

    public static void init(int[] tab) {
        for (int i = 0; i < tab.length; i++)
            tab[i] = i + 1;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[] t = new int[n];
        init(t);
        swap2(t[0], t[n - 1]);
        mysterious(t, 0);
        //  mysteriousRecursive(t, 0);
    }

}