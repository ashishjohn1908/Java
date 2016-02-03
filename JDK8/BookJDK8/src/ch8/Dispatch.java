package ch8;

// Dynamic Method Dispatch
class A7 {
    void callme() {
        System.out.println("Inside A's callme method");
    }
}

class B7 extends A7 {
    // override callme()
    void callme() {
        System.out.println("Inside B's callme method");
    }
}

class C7 extends A7 {
    // override callme()
    void callme() {
        System.out.println("Inside C's callme method");
    }
}

class Dispatch {
    public static void main(String args[]) {
        A7 a = new A7(); // object of type A
        B7 b = new B7(); // object of type B
        C7 c = new C7(); // object of type C
        A7 r; // obtain a reference of type A

        r = a; // r refers to an A object
        r.callme(); // calls A's version of callme

        r = b; // r refers to a B object
        r.callme(); // calls B's version of callme

        r = c; // r refers to a C object
        r.callme(); // calls C's version of callme
    }
}
