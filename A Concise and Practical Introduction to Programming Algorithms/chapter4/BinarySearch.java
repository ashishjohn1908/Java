class BinarySearch {

    static int Dichotomy(int[] array, int left, int right, int key) {
        if (left > right)
            return -1;

        int m = (left + right) / 2;

        if (array[m] == key) {
            return m;
        } else {
            if (array[m] < key) return Dichotomy(array, m + 1, right, key);
            else return Dichotomy(array, left, m - 1, key);
        }
    }

    static int DichotomicSearch(int[] array, int key) {
        return Dichotomy(array, 0, array.length - 1, key);
    }

    public static void main(String[] args) {
        int[] v = {1, 6, 9, 12, 45, 67, 76, 80, 95};

        System.out.println("Seeking for element 6: Position " + DichotomicSearch(v, 6));
        System.out.println("Seeking for element 80: Position " + DichotomicSearch(v, 80));
        System.out.println("Seeking for element 33: Position " + DichotomicSearch(v, 33));
    }
}