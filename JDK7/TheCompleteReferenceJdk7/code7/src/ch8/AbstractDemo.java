package ch8;
// A Simple demonstration of abstract.
abstract class A6 {
  abstract void callme();

  // concrete methods are still allowed in abstract classes
  void callmetoo() {
    System.out.println("This is a concrete method.");
  }
}

class B6 extends A6 {
  void callme() {
    System.out.println("B's implementation of callme.");
  }
}

class AbstractDemo {
  public static void main(String args[]) {
    B6 b = new B6();

    b.callme();
    b.callmetoo();
  }
}