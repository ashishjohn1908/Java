package ch14;

// Demonstrate a raw type.
class Gen1<T> {
    T ob; // declare an object of type T

    // Pass the constructor a reference to
    // an object of type T.
    Gen1(T o) {
        ob = o;
    }

    // Return ob.
    T getob() {
        return ob;
    }
}

// Demonstrate raw type.
class RawDemo {
    public static void main(String args[]) {

        // Create a Gen object for Integers.
        Gen1<Integer> iOb = new Gen1<Integer>(88);

        // Create a Gen object for Strings.
        Gen1<String> strOb = new Gen1<String>("Generics Test");

        // Create a raw-type Gen object and give it
        // a Double value.
        Gen1 raw = new Gen1(new Double(98.6));

        // Cast here is necessary because type is unknown.
        double d = (Double) raw.getob();
        System.out.println("value: " + d);

        // The use of a raw type can lead to run-time.
        // exceptions.  Here are some examples.

        // The following cast causes a run-time error!
//    int i = (Integer) raw.getob(); // run-time error

        // This assigment overrides type safety.
        strOb = raw; // OK, but potentially wrong
//    String str = strOb.getob(); // run-time error

        // This assingment also overrides type safety.
        raw = iOb; // OK, but potentially wrong
//    d = (Double) raw.getob(); // run-time error
    }
}
