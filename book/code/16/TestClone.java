class TestClone {
// Override the clone() method.

    static class CloneDemo implements Cloneable {
        int a;
        double b;

        // clone() is now overridden and is public.
        protected Object clone() {
            try {
                // call clone in Object.
                return super.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println("Cloning not allowed.");
                return this;
            }
        }
    }


    public static void main(String args[]) {
        CloneDemo x1 = new CloneDemo();
        CloneDemo x2;

        x1.a = 10;
        x1.b = 20.98;

        // here, clone() is called directly.
        x2 = (CloneDemo) x1.clone();

        System.out.println("x1: " + x1.a + " " + x1.b);
        System.out.println("x2: " + x2.a + " " + x2.b);
    }
}