class KnapsackNestedLoops {
    public static void main(String[] argArray) {
        int W1 = 3, W2 = 4, W3 = 5, W4 = 6, W5 = 2; // respective weights
        int W = 11; // weight capacity of the knapsack
        int bagWeight;//  weight of `current' configuration

        for (int i1 = 0; i1 <= 1; i1++) {
            for (int i2 = 0; i2 <= 1; i2++) {
                for (int i3 = 0; i3 <= 1; i3++) {
                    for (int i4 = 0; i4 <= 1; i4++) {
                        for (int i5 = 0; i5 <= 1; i5++) {
                            bagWeight = i1 * W1 + i2 * W2 + i3 * W3 + i4 * W4 + i5 * W5;

                            // Does the current selection match the sack capacity
                            if (bagWeight == W) {
                                System.out.println("Solution:" + i1 + " " + i2 + " " + i3 + " " + i4 + " " + i5);
                            }
                        }
                    }
                }
            }
        }
    }

}