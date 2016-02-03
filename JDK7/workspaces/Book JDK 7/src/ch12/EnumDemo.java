package ch12;
// An enumeration of apple varieties.
enum Apple2 {
  Jonathan, GoldenDel, RedDel, Winesap, Cortland
}

class EnumDemo {
  public static void main(String args[])
  {
    Apple2 ap;

    ap = Apple2.RedDel;

    // Output an enum2 value.
    System.out.println("Value of ap: " + ap);
    System.out.println();

    ap = Apple2.GoldenDel;

    // Compare two enum2 values.
    if(ap == Apple2.GoldenDel)
      System.out.println("ap contains GoldenDel.\n");

    // Use an enum2 to control a switch statement.
    switch(ap) {
      case Jonathan:
        System.out.println("Jonathan is red.");
        break;
      case GoldenDel:
        System.out.println("Golden Delicious is yellow.");
        break;
      case RedDel:
        System.out.println("Red Delicious is red.");
        break;
      case Winesap:
        System.out.println("Winesap is red.");
        break;
      case Cortland:
        System.out.println("Cortland is red.");
        break;
    }
  }
}
