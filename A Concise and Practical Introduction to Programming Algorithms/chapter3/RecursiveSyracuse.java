class RecursiveSyracuse {

    public static double Syracuse(int n) {
        if (n == 1){
            return 1;
        } else if (n % 2 == 0) {
            return 1 + Syracuse(n / 2); // even
        } else {
            return (1 + Syracuse(3 * n + 1) / 2);
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 10000; i++) {
            System.out.println("Test termination for " + i);
            Syracuse(i);
        }
    }
}