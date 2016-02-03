package ch9;
// A simple package
//package MyPack;

class Balance1 {
  String name;
  double bal;

  Balance1(String n, double b) {
    name = n;
    bal = b;
  }

  void show() {
    if(bal<0)
      System.out.print("-->> ");
    System.out.println(name + ": $" + bal);
  }
}

class AccountBalance {
  public static void main(String args[]) {
    Balance1 current[] = new Balance1[3];

    current[0] = new Balance1("K. J. Fielding", 123.23);
    current[1] = new Balance1("Will Tell", 157.02);
    current[2] = new Balance1("Tom Jackson", -12.33);

    for(int i=0; i<3; i++) current[i].show();
  }
}