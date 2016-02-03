class FunctionStack {

    static double G(double x) {
        double result;
        result = Math.pow(x, 2.5);
        return result;
    }

    static double F(double x) {
        double result;
        result = 1.0 + G(x * x);
        return result;
    }

    public static void main(String[] args) {
        double y;
        y = F(5.0);
        System.out.println("y=" + y);
    }
}