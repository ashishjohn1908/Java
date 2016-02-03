class FibonacciTerminalRecursion {

    static int FibonacciRecTerm(int n, int i, int a, int b) {
        if (n == i) return a;
        else return FibonacciRecTerm(n, i + 1, a + b, a);
    }

    static int FibonacciLaunch(int n) {
        if (n <= 1) return n;
        else return FibonacciRecTerm(n, 0, 0, 1);
    }

    public static void main(String[] arg) {
        System.out.println("Fibonacci(7)=" + FibonacciLaunch(7));
    }
}