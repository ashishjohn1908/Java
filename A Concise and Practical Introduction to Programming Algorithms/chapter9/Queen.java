class Queen {
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

    static boolean wrongPos(int i1, int j1, int i2, int j2) {
        return (i1 == i2 ||
                j1 == j2 ||
                Math.abs(i1 - i2) == Math.abs(j1 - j2));
    }

    // Place safely queen i at column j?
    static boolean safeMove(int i, int j) {
        boolean result = true;

        for (int k = 0; k < i; k++)
            result = result && !wrongPos(i, j, k, queen[k]);

        return result;
    }

    static boolean search(int row) {
        boolean result = false;

        if (row == n) {
            displayChessboard();
            nbsol++;
        } else {
            int j = 0;
            while (!result && j < n) {
                if (safeMove(row, j)) {
                    queen[row] = j;
                    result = search(row + 1);
                }
                // Backtracking here
                j++; // explore all columns
            }
        }
        return result;
    }

    public static void main(String[] arguments) {
        nbsol = 0;
        search(0);
        System.out.println("Total number of solutions:" + nbsol);
    }

}