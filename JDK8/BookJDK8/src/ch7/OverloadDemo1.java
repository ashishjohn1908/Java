package ch7;

// Automatic type conversions apply to overloading.
class Overload1 {
    void test() {
        System.out.println("No parameters");
    }

    // Overload test for two integer parameters.
    void test(int a, int b) {
        System.out.println("a and b: " + a + " " + b);
    }

    // overload test for a double parameter and return type
    void test(double a) {
        System.out.println("Inside test(double) a: " + a);
    }
}

class OverloadDemo1 {
    public static void main(String args[]) {
        Overload1 ob = new Overload1();
        int i = 88;

        ob.test();
        ob.test(10, 20);

        ob.test(i); // this will invoke test(double)
        ob.test(123.2); // this will invoke test(double)
    }
}
