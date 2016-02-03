class FunctionSideEffect {
    static int x = 0;

    static void F() {
        x++;
    }

    static void G() {
        --x;
    }

    public static void main(String[] args) {
        F();
        G();
        F();
        System.out.println("value of x:" + x);
    }
}