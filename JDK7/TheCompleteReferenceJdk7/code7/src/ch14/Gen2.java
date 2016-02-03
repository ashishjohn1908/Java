package ch14;
// A simple generic class hierarchy.
class Gen10<T> {
  T ob;

  Gen10(T o) {
    ob = o;
  }

  // Return ob.
  T getob() {
    return ob;
  }
}

// A subclass of Gen.
class Gen2<T> extends Gen10<T> {
  Gen2(T o) {
    super(o);
  }
}