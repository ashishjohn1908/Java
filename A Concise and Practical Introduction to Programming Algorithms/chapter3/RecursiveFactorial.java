class RecursiveFactorial {
    public static int Factorial(int n) {
        if (n == 0)
            return 1;
        else
            return n * Factorial(n - 1);
    }

    public static void main(String[] arg) {
        System.out.println("5!=" + Factorial(5));
    }
}