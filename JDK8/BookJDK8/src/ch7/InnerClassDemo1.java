package ch7;

// This program will not compile.
class Outer1 {
    int outer_x = 100;

    void test() {
        Inner inner = new Inner();
        inner.display();
        showy();
    }

    void showy() {
        System.out.println("show: inner_y = " + new Outer1.Inner().y); // error, y not known here!
    }

    // this is an innner class
    class Inner {
        int y = 10; // y is local to Inner

        void display() {
            System.out.println("display: outer_x = " + outer_x);
        }
    }
}

class InnerClassDemo1 {
    public static void main(String args[]) {
        Outer1 outer = new Outer1();
        outer.test();
    }
}
