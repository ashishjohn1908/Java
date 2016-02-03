package ch12;
// Use the built-in enumeration methods.
// An enumeration of apple varieties.
enum Apple3 {
  Jonathan, GoldenDel, RedDel, Winesap, Cortland
}

class EnumDemo2 {
  public static void main(String args[])
  {
    Apple3 ap;

    System.out.println("Here are all Apple constants");

    // use values()
    Apple3 allapples[] = Apple3.values();
    for(Apple3 a : allapples)
      System.out.println(a);

    System.out.println();

    // use valueOf()
    ap = Apple3.valueOf("Winesap");
    System.out.println("ap contains " + ap);

  }
}