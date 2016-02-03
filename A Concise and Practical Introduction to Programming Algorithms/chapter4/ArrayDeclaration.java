class ArrayDeclaration {

    static int digit[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    static double x[] = {Math.PI, Math.E, 1.0, 0.0};
    static boolean prime[] = {false, true, true, true, false, true, false, true, false, false};
    static int y[];

    static void MyFunction(int n) {
        // Allocate an array of size n
        y = new int[2 * n];
    }

    public static void main(String[] args) {
        MyFunction(15);
        // We recreate and initialize array y;
        MyFunction(20);


        int[] v = {0, 1, 2, 3, 4};
        int[] t = v;
        t[2]++;
        System.out.println(t[2]++);

    }
}