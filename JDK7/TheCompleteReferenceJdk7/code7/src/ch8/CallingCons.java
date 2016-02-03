package ch8;
// Demonstrate when constructors are called.

// Create a super class.
class A1 {
  A1() {
    System.out.println("Inside A's constructor.");
  }
}

// Create a subclass by extending class A.
class B1 extends A1 {
  B1() {
    System.out.println("Inside B's constructor.");
  }
}

// Create another subclass by extending B.
class C1 extends B1 {
  C1() {
    System.out.println("Inside C's constructor.");
  }
}

class CallingCons {
  public static void main(String args[]) {
    C1 c = new C1();
  }
}