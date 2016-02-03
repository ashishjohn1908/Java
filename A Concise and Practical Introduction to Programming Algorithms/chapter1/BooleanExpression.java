class BooleanExpression {

    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;

        boolean expr1 = a || b;
        boolean expr2 = a && b;

        System.out.print("a||b = ");
        System.out.println(expr1);

        System.out.print("a&&b = ");
        System.out.println(expr2);
    }
}

