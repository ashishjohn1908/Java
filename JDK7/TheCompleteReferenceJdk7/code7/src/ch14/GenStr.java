package ch14;
// Here, T is bound by Object by default.
class Gen7<T> {
  T ob; // here, T will be replaced by Object

  Gen7(T o) {
    ob = o;
  }

  // Return ob.
  T getob() {
    return ob;
  }
}

// Here, T is bound by String.
class GenStr<T extends String> {
  T str; // here, T will be replaced by String

  GenStr(T o) {
    str = o;
  }

  T getstr() { return str; }
}
