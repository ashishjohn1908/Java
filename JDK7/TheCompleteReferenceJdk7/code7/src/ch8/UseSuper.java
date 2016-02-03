package ch8;
// Using super to overcome name hiding.
class A4 {
  int i;
}

// Create a subclass by extending class A.
class B4 extends A4 {
  int i; // this i hides the i in A

  B4(int a, int b) {
    super.i = a; // i in A
    i = b; // i in B
  }

  void show() {
    System.out.println("i in superclass: " + super.i);
    System.out.println("i in subclass: " + i);
  }
}

class UseSuper {
  public static void main(String args[]) {
    B4 subOb = new B4(1, 2);

    subOb.show();
  }
}
