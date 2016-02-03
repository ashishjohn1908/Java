package ch8;
// Create a subclass by extending class A.
class B8 extends A {
  int k;

  B8(int a, int b, int c) {
    super(a, b);
    k = c;
  }

  // overload show()
  void show(String msg) {
    System.out.println(msg + k);
  }
}

class Override1 {
  public static void main(String args[]) {
    B8 subOb = new B8(1, 2, 3);

    subOb.show("This is k: "); // this calls show() in B
    subOb.show(); // this calls show() in A
  }
}
