class Queen1 {
    static final int n = 8;
    static int[] queen = new int[n]; // position (i,queen[i])
    static int nbsol;

    static void displayChessboard() {
        int i, j;

        System.out.println("");

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (queen[i] != j) System.out.print("0");
                else System.out.print("1");
            }
            System.out.println("");
        }
    }

    static boolean wrongPos(int i1, int j1, int i2, int j2) {return true;}

    static boolean safeMove(int i, int j) { return true;}

    static boolean search(int row) {return true;}

    public static void main(String[] arguments) {
        nbsol = 0;
        search(0);
        System.out.println("Total number of solutions:" + nbsol);
    }

}