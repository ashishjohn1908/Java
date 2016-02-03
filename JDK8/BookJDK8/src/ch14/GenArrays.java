package ch14;

// Generics and arrays.
class Gen13<T extends Number> {
    T ob;

    T vals[]; // OK

    Gen13(T o, T[] nums) {
        ob = o;

        // This statement is illegal.
        // vals = new T[10]; // can't create an array of T

        // But, this statement is OK.
        vals = nums; // OK to assign reference to existent array
    }
}

class GenArrays {
    public static void main(String args[]) {
        Integer n[] = {1, 2, 3, 4, 5};

        Gen13<Integer> iOb = new Gen13<Integer>(50, n);

        // Can't create an array of type-specific generic references.
        // Gen<Integer> gens[] = new Gen<Integer>[10]; // Wrong!

        // This is OK.
        Gen13<?> gens[] = new Gen13<?>[10]; // OK
    }
}

