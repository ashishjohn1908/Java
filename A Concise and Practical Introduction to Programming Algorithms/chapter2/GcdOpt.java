class GCDOpt {

    static int gcd(int a, int b) {
        return (b != 0 ? gcd(b, a % b) : a);
    }

    public static void main(String[] args) {
        System.out.println(gcd(1071, 1029));
    }

}