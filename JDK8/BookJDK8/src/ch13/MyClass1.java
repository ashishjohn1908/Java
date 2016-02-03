package ch13;

/**
 * Created by plamen on 24/08/2014.
 */
class MyClass1 {
    int a;
    int b;

    // initialize a and b individually
    MyClass1(int i, int j) {
        a = i;
        b = j;
    }

    // initialize a and b to the same value
    MyClass1(int i) {
        this(i, i); // invokes MyClass(i, i)
    }

    // give a and b default values of 0
    MyClass1() {
        this(0); // invokes MyClass(0)
    }
}
