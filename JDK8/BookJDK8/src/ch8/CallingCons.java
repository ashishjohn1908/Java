package ch8;

// Demonstrate when constructors are called.

// Create a super class.
class A4 {
    A4() {
        System.out.println("Inside A's constructor.");
    }
}

// Create a subclass by extending class A.
class B4 extends A4 {
    B4() {
        System.out.println("Inside B's constructor.");
    }
}

// Create another subclass by extending B.
class C4 extends B4 {
    C4() {
        System.out.println("Inside C's constructor.");
    }
}

class CallingCons {
    public static void main(String args[]) {
        C4 c = new C4();
    }
}
