class ExoArray {

    static void permute(String s1, String s2) {
        String tmp = s1;
        s1 = s2;
        s2 = tmp;
    }


    static void permute(String[] tab, int i, int j) {
        String tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }


    public static void main(String args[]) {
        String[] array = {"shark", "dog", "cat", "crocodile"};
        permute(array[0], array[1]);
        System.out.println(array[0] + " " + array[1]);

        permute(array, 0, 1);
        System.out.println(array[0] + " " + array[1]);
    }

}