package ch8;

// A Simple demonstration of abstract.
abstract class A8 {
    abstract void callme();

    // concrete methods are still allowed in abstract classes
    void callmetoo() {
        System.out.println("This is a concrete method.");
    }
}

class B8 extends A8 {
    void callme() {
        System.out.println("B's implementation of callme.");
    }
}

class AbstractDemo {
    public static void main(String args[]) {
        B8 b = new B8();

        b.callme();
        b.callmetoo();
    }
}
