package ch14;

// Use the instanceof operator with a generic class hierarchy.
class Gen6<T> {
    T ob;

    Gen6(T o) {
        ob = o;
    }

    // Return ob.
    T getob() {
        return ob;
    }
}

// A subclass of Gen.
class Gen7<T> extends Gen6<T> {
    Gen7(T o) {
        super(o);
    }
}

// Demonstrate run-time type ID implications of generic
// class hierarchy.
class HierDemo3 {
    public static void main(String args[]) {

        // Create a Gen object for Integers.
        Gen6<Integer> iOb = new Gen6<Integer>(88);

        // Create a Gen2 object for Integers.
        Gen7<Integer> iOb2 = new Gen7<Integer>(99);

        // Create a Gen2 object for Strings.
        Gen7<String> strOb2 = new Gen7<String>("Generics Test");

        // See if iOb2 is some form of Gen2.
        if (iOb2 instanceof Gen7<?>)
            System.out.println("iOb2 is instance of Gen2");

        // See if iOb2 is some form of Gen.
        if (iOb2 instanceof Gen6<?>)
            System.out.println("iOb2 is instance of Gen");

        System.out.println();

        // See if strOb2 is a Gen2.
        if (strOb2 instanceof Gen7<?>)
            System.out.println("strOb is instance of Gen2");

        // See if strOb2 is a Gen.
        if (strOb2 instanceof Gen6<?>)
            System.out.println("strOb is instance of Gen");

        System.out.println();

        // See if iOb is an instance of Gen2, which it is not.
        if (iOb instanceof Gen7<?>)
            System.out.println("iOb is instance of Gen2");

        // See if iOb is an instance of Gen, which it is.
        if (iOb instanceof Gen6<?>)
            System.out.println("iOb is instance of Gen");

        // The following can't be compiled because
        // generic type info does not exist at run-time.
//    if(iOb2 instanceof Gen2<Integer>)
//      System.out.println("iOb2 is instance of Gen2<Integer>");
    }
}

