package ch14;
// Use the instanceof operator with a generic class hierarchy.
class Gen11<T> {
  T ob;

  Gen11(T o) {
    ob = o;
  }

  // Return ob.
  T getob() {
    return ob;
  }
}

// A subclass of Gen.
class Gen20<T> extends Gen11<T> {
  Gen20(T o) {
    super(o);
  }
}

// Demonstrate run-time type ID implications of generic
// class hierarchy.
class HierDemo3 {
  public static void main(String args[]) {

    // Create a Gen object for Integers.
    Gen11<Integer> iOb = new Gen11<Integer>(88);

    // Create a Gen2 object for Integers.
    Gen20<Integer> iOb2 = new Gen20<Integer>(99);

    // Create a Gen2 object for Strings.
    Gen20<String> strOb2 = new Gen20<String>("Generics Test");

    // See if iOb2 is some form of Gen2.
    if(iOb2 instanceof Gen20<?>)
      System.out.println("iOb2 is instance of Gen2");

    // See if iOb2 is some form of Gen.
    if(iOb2 instanceof Gen11<?>)
      System.out.println("iOb2 is instance of Gen");

    System.out.println();

    // See if strOb2 is a Gen2.
    if(strOb2 instanceof Gen20<?>)
      System.out.println("strOb is instance of Gen2");

    // See if strOb2 is a Gen.
    if(strOb2 instanceof Gen11<?>)
      System.out.println("strOb is instance of Gen");

    System.out.println();

    // See if iOb is an instance of Gen2, which it is not.
    if(iOb instanceof Gen20<?>)
      System.out.println("iOb is instance of Gen2");

    // See if iOb is an instance of Gen, which it is.
    if(iOb instanceof Gen11<?>)
      System.out.println("iOb is instance of Gen");

    // The following can't be compiled because
    // generic type info does not exist at run-time.
  //  if(iOb2 instanceof Gen2<Integer>)
  //    System.out.println("iOb2 is instance of Gen2<Integer>");
  }
}