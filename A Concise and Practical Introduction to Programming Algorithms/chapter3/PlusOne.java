class PlusOne {

    static double plusone(int n) {
        return n + 1.0;
    }

    static double plusone(double x) {
        return x + 1.0;
    }

    static double plusone(String s) {
        return Double.parseDouble(s) + 1.0;
    }

    public static void main(String[] args) {
        System.out.println(plusone(5));
        System.out.println(plusone(6.23));
        System.out.println(plusone("123.2"));
    }
}