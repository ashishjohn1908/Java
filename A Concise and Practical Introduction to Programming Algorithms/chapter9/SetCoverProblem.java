class SetCover {
    int nbelements;
    int nbsubsets;
    boolean[][] incidenceMatrix;

    SetCover(int nn, int mm) {
        this.nbelements = nn;
        this.nbsubsets = mm;

        incidenceMatrix = new boolean[nbsubsets][nbelements];

        for (int i = 0; i < nbsubsets; i++)
            for (int j = 0; j < nbelements; j++)
                incidenceMatrix[i][j] = false;
    }

    void SetSubsets(int[][] array) {
        for (int j = 0; j < array.length; j++) {
            for (int i = 0; i < array[j].length; i++)
                incidenceMatrix[j][array[j][i]] = true;
        }
    }

    void Display() {
        for (int i = 0; i < nbsubsets; i++) {

            for (int j = 0; j < nbelements; j++)
                if (incidenceMatrix[i][j]) System.out.print("1");
                else System.out.print("0");
            System.out.println("");
        }
    }

    // Number of covered element by subset i
    int Cover(int i) {
        int nbEl = 0;

        for (int j = 0; j < nbelements; j++)
            if (incidenceMatrix[i][j]) ++nbEl;

        return nbEl;
    }

    // Report the current largest subset
    int LargestSubset() {
        int i, nbel, max, select;

        max = -1;
        select = -1;

        for (i = 0; i < nbsubsets; i++) {
            nbel = Cover(i);
            if (nbel > max) {
                max = nbel;
                select = i;
            }
        }

        return select;
    }

    // Update the incidence matrix
    void Update(int sel) {
        int i, j;

        for (i = 0; i < nbsubsets; i++) {
            if (i != sel) {
                for (j = 0; j < nbelements; j++)
                    if (incidenceMatrix[sel][j]) incidenceMatrix[i][j] = false;
            }
        }
        for (j = 0; j < nbelements; j++)
            incidenceMatrix[sel][j] = false;
    }


}


class SetCoverProblem {

    static boolean[] GreedySCP(SetCover problem) {
        boolean[] result = new boolean[problem.nbsubsets];
        int cover = 0;
        int select;

        for (int i = 0; i < problem.nbsubsets; i++)
            result[i] = false;

        while (cover != problem.nbelements) {
            // Choose largest not-yet covered subset
            select = problem.LargestSubset();
            result[select] = true;

            // Update covered matrix
            cover += problem.Cover(select);

            // Update incidence matrix
            problem.Update(select);

            System.out.println("Selected " + select + " Number of covered elements=" + cover);
            problem.Display();
        }

        return result;
    }

    public static void main(String[] args) {
        /*

      */

        /*
       *int [] [] subsets={{0,1,2,3,4,5,6},{7,8,9,10,11,12,13},
                          {0,7},{1,2,8,9},{3,4,5,6,10,11,12,13}};
                              SetCover setcover=new SetCover(14,5);
      */

        int[][] subsets = {{0, 1, 3}, {2, 3, 4}, {0, 2, 5}, {1, 2, 4}, {3, 4, 5}, {0, 2}};
        SetCover setcover = new SetCover(6, 6);

        setcover.SetSubsets(subsets);

        System.out.println("Set cover problem:");
        setcover.Display();

        boolean[] solution = GreedySCP(setcover);

        System.out.print("Solution:");
        for (int i = 0; i < setcover.nbsubsets; i++)
            if (solution[i]) System.out.print(" " + i);
        System.out.println("");
    }
}