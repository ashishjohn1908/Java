package ch14;

// Overriding a generic method in a generic class.
class Gen8<T> {
    T ob; // declare an object of type T

    // Pass the constructor a reference to
    // an object of type T.
    Gen8(T o) {
        ob = o;
    }

    // Return ob.
    T getob() {
        System.out.print("Gen's getob(): ");
        return ob;
    }
}

// A subclass of Gen that overrides getob().
class Gen9<T> extends Gen8<T> {

    Gen9(T o) {
        super(o);
    }

    // Override getob().
    T getob() {
        System.out.print("Gen2's getob(): ");
        return ob;
    }
}

// Demonstrate generic method override.
class OverrideDemo {
    public static void main(String args[]) {

        // Create a Gen object for Integers.
        Gen8<Integer> iOb = new Gen8<Integer>(88);

        // Create a Gen2 object for Integers.
        Gen9<Integer> iOb2 = new Gen9<Integer>(99);

        // Create a Gen2 object for Strings.
        Gen9<String> strOb2 = new Gen9<String>("Generics Test");

        System.out.println(iOb.getob());
        System.out.println(iOb2.getob());
        System.out.println(strOb2.getob());
    }
}

