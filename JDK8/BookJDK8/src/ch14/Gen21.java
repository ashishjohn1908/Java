package ch14;

// A simple generic class hierarchy.
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
class Gen21<T> extends Gen11<T> {
    Gen21(T o) {
        super(o);
    }
}
