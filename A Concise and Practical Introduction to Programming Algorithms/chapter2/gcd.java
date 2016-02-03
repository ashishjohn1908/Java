class gcd {
    public static void main(String[] arg) {
// Parse arguments into integer parameters
        int a = Integer.parseInt(arg[0]);
        int b = Integer.parseInt(arg[1]);
        System.out.println("Computing GCD(" + a + "," + b + ")");

        while (a != b) {
            if (a > b) a = a - b;
            else b = b - a;
        }
// Display to console
        System.out.println("Greatest common divisor is " + a);
    }
}
