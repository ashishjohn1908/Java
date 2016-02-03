class GreedyKnapsack {
    static int n = 10;
    static int[] W = {5, 3, 8, 12, 5, 7, 9, 2, 3, 6};
    static int[] U = {3, 5, 2, 4, 7, 3, 2, 3, 5, 7};
    static int wKnapsack = 20;

    // Greedy algorithm for approximating a best solution
    public static void main(String[] argArray) {
        double[] gain = new double[n];
        double g;
        int nSel = 0;
        int u = 0;
        int w = 0;
        int i, winner;
        boolean[] chosen = new boolean[n];

        System.out.println("Maximum weight of the knapsack:" + wKnapsack);

        for (i = 0; i < n; i++) {
            gain[i] = U[i] / (double) W[i];
            chosen[i] = false;
        }

        while (nSel <= n && w < wKnapsack) {
            g = 0.0d;
            winner = -1;

            for (i = 0; i < n; i++) {
                if (!chosen[i] && w + W[i] <= wKnapsack)
                    System.out.println(i + " Weight " + W[i] + " Utility " + U[i] + " Gain[" + i + "]=" + gain[i]);
            }

            // Search best gain in the remaining collection of objects
            for (i = 0; i < n; i++)
                if (!chosen[i] && gain[i] > g && wKnapsack - (w + W[i]) >= 0) {
                    g = gain[i];
                    winner = i;
                }

            // Can we add this object to the knapsack
            if (winner != -1 && w + W[winner] < wKnapsack) {
                u += U[winner];
                w += W[winner];
                chosen[winner] = true;
                nSel++;
                System.out.println("Selected object " + winner + ", updating #weights=" + w + " #utility=" + u);
            } else break;
        }
    }
}