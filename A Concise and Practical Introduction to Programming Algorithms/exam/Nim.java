public class Nim {

    public static int binaryToDecimal(
            int[] binaryRepresentation) {
        int n = 0;
        int p2 = 1;
        for (int i = 0; i < binaryRepresentation.length; i++) {
            n = n + binaryRepresentation[i] * p2;
            p2 = p2 * 2;
        }
        return n;
    }

    public static void decimalToBinaryAux(int n, int i,
                                          int[] binaryRepresentation) {
        if (n > 0) {
            binaryRepresentation[i] = n % 2;
            decimalToBinaryAux(n / 2, i + 1, binaryRepresentation);
        }
    }

    public static int[] decimalToBinary(int n, int k) {
        int[] binaryRepresentation = new int[k];
        decimalToBinaryAux(n, 0, binaryRepresentation);
        return binaryRepresentation;
    }

    public static int getBinaryLength(int[] decimalTab) {
        // search the largest value
        int max = decimalTab[0];
        for (int i = 1; i < decimalTab.length; i++) {
            if (decimalTab[i] > max) {
                max = decimalTab[i];
            }
        }
        int k = 0;
        int p = 1;
        while (p <= max) {
            ++k;
            p *= 2;
        }
        return k;
    }

    public static int[][] decomposition(int[] decimalTab) {
        int k = getBinaryLength(decimalTab);
        int[][] binaryTab = new int[decimalTab.length][];
        for (int i = 0; i < decimalTab.length; i++) {
            binaryTab[i] = decimalToBinary(decimalTab[i], k);
        }
        return binaryTab;
    }

    public static int[] binaryGrundy(int[][] binaryTab) {
        int k = binaryTab[0].length;
        int[] grundyBinaire = new int[k];
        for (int j = 0; j < k; j++) {
            for (int i = 0; i < binaryTab.length; i++) {
                grundyBinaire[j] =
                        (grundyBinaire[j] + binaryTab[i][j]) % 2;
            }
        }
        return grundyBinaire;
    }

    public static int Grundy(int[] decimalTab) {
        int[][] binaryTab = decomposition(decimalTab);
        return binaryToDecimal(binaryGrundy(binaryTab));
    }

    public static int[] loserPick(int[] decimalTab) {
        int i = 0;
        while (decimalTab[i] == 0) {
            i++;
        }
        int[] play = {i, 1};
        return play;
    }

    public static int[] pick(int[] decimalTab) {
        int[][] binaryTab = decomposition(decimalTab);
        int k = binaryTab[0].length;
        int[] grundyBin = binaryGrundy(binaryTab);

        if (binaryToDecimal(grundyBin) == 0) {
            return loserPick(decimalTab);
        }
        int j = k - 1;
        while (grundyBin[j] == 0) {
            j--;
        }
        int i = 0;
        while (binaryTab[i][j] == 0) {
            i++;
        }
        for (int h = 0; h < grundyBin.length; h++) {
            if (grundyBin[h] == 1) {
                binaryTab[i][h] = 1 - binaryTab[i][h];
            }
        }
        int[] play = new int[2];
        play[0] = i;
        play[1] = decimalTab[i] -
                binaryToDecimal(binaryTab[i]);
        return play;
    }

    public static void main(String[] args) {
        int[] game = {6, 9, 1, 2};
        do {
            int tt = 0;
            System.out.println();
            for (int i = 0; i < game.length; ++i) {
                System.out.print(game[i] + " ");
                tt += game[i];
            }
            System.out.println();
            if (tt <= 0)
                break;
            System.out.println(Grundy(game));
            int[] play = pick(game);
            System.out.println(play[0] + " " + play[1]);
            game[play[0]] -= play[1];
        } while (true);
    }
}
