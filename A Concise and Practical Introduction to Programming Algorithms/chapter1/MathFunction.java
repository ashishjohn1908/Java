class MathFunction {
    public static void main(String[] args) {
        int q = 2 / 3;
        double qq = 2 / 3;
        double qqq = 2 / 3.0;

        System.out.println(7 + (9 * 6));

        System.out.println(q);
        System.out.println(qq);
        System.out.println(qqq);


        double x = Math.E;
        double fx = Math.log(x);
        System.out.print("Is this precisely 1 or is there numerical errors? ");
        System.out.println(fx);


        x = Math.PI / 15.0;
        System.out.println("x : " + x);
        fx = Math.sin(x) * Math.sin(x) + Math.cos(x) * Math.cos(x);
        System.out.print("What about this trigonometric equality (should be 1) ?");
        System.out.println(fx);
    }
}