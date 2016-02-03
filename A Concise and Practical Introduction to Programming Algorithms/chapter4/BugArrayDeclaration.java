class BugArrayDeclaration {

    static long signature(char[] X, int m, int i) {
        long result = 0;
        for (int j = i; j < i + m; j++)
            result += X[j];
        return result;
    }

    public static void main(String[] ttt) {
        String stringt = "A simple test string";
        char[] T = stringt.toCharArray();

        System.out.println(signature(T, 5, 0));

        int[] array;
        int[] array2 = null;
        array = array2;
        array[0] = 1;


    }
}