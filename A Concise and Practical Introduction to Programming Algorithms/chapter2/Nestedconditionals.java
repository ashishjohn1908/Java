class NestedConditional {
    public static void main(String[] arg) {
        double d = -1.0;

        final String msg = "My Application";

        if (d > 0) {
            System.out.println("larger");
        } else {
            if (d < 0) {
                System.out.println("smaller");
            } else {
                System.out.println("identical");
            }
        }


        if (d > 0) System.out.println("larger");
        else if (d < 0)
            System.out.println("smaller");
        else
            System.out.println("identical");

    }
}