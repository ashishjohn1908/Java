package ch8;
// Dynamic Method Dispatch
class A3 {
   void callme() {
     System.out.println("Inside A's callme method");
  }
}

class B3 extends A3 {
  // override callme()
  void callme() {
    System.out.println("Inside B's callme method");
  }
}

class C3 extends A3 {
  // override callme()
  void callme() {
    System.out.println("Inside C's callme method");
  }
}

class Dispatch {
  public static void main(String args[]) {
    A3 a = new A3(); // object of type A
    B3 b = new B3(); // object of type B
    C3 c = new C3(); // object of type C
    A3 r; // obtain a reference of type A

    r = a; // r refers to an A object
    r.callme(); // calls A's version of callme

    r = b; // r refers to a B object
    r.callme(); // calls B's version of callme

    r = c; // r refers to a C object
    r.callme(); // calls C's version of callme
  }
}
