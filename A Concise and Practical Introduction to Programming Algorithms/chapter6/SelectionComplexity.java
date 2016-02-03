class SelectionSort {
    static int nboperations;

    static boolean GreaterThan(int a, int b) {
        nboperations++;
        return (a > b);
    }

    static void swap(int[] array, int i, int j) {
        nboperations++;
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    static void SelectionSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (GreaterThan(array[i], array[j]))
                    swap(array, i, j);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        nboperations = 0;

        SelectionSort(array);

        for (int i = 0; i < array.length; i++)
            System.out.print(array[i] + " ");
        System.out.println("");

        System.out.println("Number of operations:" + nboperations);
        int nb = 2 * array.length * (array.length - 1) / 2;
        System.out.println("Number of operations:" + nb);
    }


}