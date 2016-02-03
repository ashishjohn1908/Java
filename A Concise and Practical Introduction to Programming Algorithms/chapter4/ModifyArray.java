class ModifyArray {

    static void swap(int[] t, int i, int j) {
        int tmp;

        tmp = t[i];
        t[i] = t[j];
        t[j] = tmp;
    }

    static void DisplayArray(int[] x) {
        for (int i = 0; i < x.length; i++)
            System.out.print(x[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] t = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        DisplayArray(t);
        swap(t, 2, 3);
        DisplayArray(t);
    }
}