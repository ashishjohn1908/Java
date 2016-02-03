class PassByValue {

    static void F(double x) {
        x = 3;
        System.out.println("Before exiting F, value of x:" + x);
    }

    public static void main(String[] args) {
        double x = 5;
        F(x);
        System.out.println("value of x:" + x);
    }
}