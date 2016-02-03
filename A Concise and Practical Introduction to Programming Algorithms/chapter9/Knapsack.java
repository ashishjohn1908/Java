class KnapSack {
    final static int n = 10; // 10 objects
    static int[] weight = {2, 3, 5, 7, 9, 11, 4, 13, 23, 27};

    static void display(boolean[] selection, int val) {
        String msg = "";
        for (int i = 0; i < selection.length; i++) {
            if (selection[i])
                msg = msg + weight[i] + " ";
        }
        System.out.println(msg + "=" + val);
    }

    static void solveKnapSack(boolean[] chosen, int goal, int i, int total) {
        if ((i >= chosen.length) && (total != goal))
            return;

        if (total == goal) {
            display(chosen, goal);
        } else {
            chosen[i] = true;// add item first and proceed
            solveKnapSack(chosen, goal, i + 1, total + weight[i]);

            chosen[i] = false;  // and then remove it and proceed
            solveKnapSack(chosen, goal, i + 1, total);
        }
    }


    public static void main(String[] args) {
        int totalweight = 51;
        boolean[] chosen = new boolean[n];// initialized to all false

        solveKnapSack(chosen, totalweight, 0, 0);
    }

}