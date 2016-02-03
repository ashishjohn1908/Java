package ch7;
// Objects may be passed to methods.
class Test3 {
  int a, b;

  Test3(int i, int j) {
    a = i;
    b = j;
  }

  // return true if o is equal to the invoking object
  boolean equals(Test3 o) {
    if(o.a == a && o.b == b) return true;
    else return false;
  }
}

class PassOb {
  public static void main(String args[]) {
    Test3 ob1 = new Test3(100, 22);
    Test3 ob2 = new Test3(100, 22);
    Test3 ob3 = new Test3(-1, -1);

    System.out.println("ob1 == ob2: " + ob1.equals(ob2));

    System.out.println("ob1 == ob3: " + ob1.equals(ob3));
  }
}