class FibonacciSequence {

    public FibonacciSequence() throws Exception{
        System.out.println("Test");
    }

    public static int Fibonacci(int n) {
        if (n <= 1)
            return 1;
        else
            return Fibonacci(n - 1) + Fibonacci(n - 2);
    }


    public static void main(String[] args) {
        int i;
        for (i = 0; i <= 30; i++)
            System.out.print(Fibonacci(i) + " ");
    }
}