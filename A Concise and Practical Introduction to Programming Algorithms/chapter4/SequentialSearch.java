class SequentialSearch {

    static int SequentialSearch(int[] array, int key) {
        int i;
        for (i = 0; i < array.length; i++)
            if (array[i] == key)
                return i;

        return -1;
    }

    public static void main(String[] args) {
        int[] v = {1, 6, 9, 12, 45, 67, 76, 80, 95};

        System.out.println("Seeking for element 6: Position " + SequentialSearch(v, 6));
        System.out.println("Seeking for element 80: Position " + SequentialSearch(v, 80));
        System.out.println("Seeking for element 33: Position " + SequentialSearch(v, 33));
    }
}