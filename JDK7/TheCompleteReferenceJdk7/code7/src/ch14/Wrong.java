package ch14;
class Wrong<T> {
  // Wrong, no static variables of type T.
  T ob;     // static T ob;

  // Wrong, no static method can use T.
  T getob() {   //static T getob() {
    return ob;
  }
}