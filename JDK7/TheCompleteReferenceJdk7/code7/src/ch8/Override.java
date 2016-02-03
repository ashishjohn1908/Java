package ch8;
// Method overriding.
class A7 {
  int i, j;

  A7(int a, int b) {
    i = a;
    j = b;
  }

  // display i and j
  void show() {
    System.out.println("i and j: " + i + " " + j);
  }
}

class B7 extends A7 {
  int k;

  B7(int a, int b, int c) {
    super(a, b);
    k = c;
  }

  // display k -- this overrides show() in A
  void show() {
    System.out.println("k: " + k);
  }
}

class Override {
  public static void main(String args[]) {
    B7 subOb = new B7(1, 2, 3);

    subOb.show(); // this calls show() in B
  }
}
