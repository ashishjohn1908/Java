class TerminalRecursionFactorial {

    static long FactorialRecTerminal(int n, int i, int result) {
        if (n == i) return result;
        else
            return FactorialRecTerminal(n, i + 1, result * (i + 1));
    }

    static long FactorialLaunch(int n) {
        if (n <= 1) return n;
        else return FactorialRecTerminal(n, 1, 1);
    }

    public static void main(String[] args) {
        System.out.println("Factorial 10!=" + FactorialLaunch(10));
    }
}