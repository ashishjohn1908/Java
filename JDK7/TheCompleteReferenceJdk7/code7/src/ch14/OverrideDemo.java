package ch14;
// Overriding a generic method in a generic class.
class Gen4<T> {
  T ob; // declare an object of type T

  // Pass the constructor a reference to
  // an object of type T.
  Gen4(T o) {
    ob = o;
  }

  // Return ob.
  T getob() {
    System.out.print("Gen's getob(): " );
    return ob;
  }
}

// A subclass of Gen that overrides getob().
class Gen5<T> extends Gen4<T> {

  Gen5(T o) {
    super(o);
  }

  // Override getob().
  T getob() {
    System.out.print("Gen5's getob(): ");
    return ob;
  }
}

// Demonstrate generic method override.
class OverrideDemo {
  public static void main(String args[]) {

    // Create a Gen object for Integers.
    Gen4<Integer> iOb = new Gen4<Integer>(88);

    // Create a Gen2 object for Integers.
    Gen5<Integer> iOb2 = new Gen5<Integer>(99);

    // Create a Gen2 object for Strings.
    Gen5<String> strOb2 = new Gen5<String>("Generics Test");

    System.out.println(iOb.getob());
    System.out.println(iOb2.getob());
    System.out.println(strOb2.getob());
  }
}
