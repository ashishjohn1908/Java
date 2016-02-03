package ch5;
// Declare a loop control variable inside the for.
class ForTick1 {
  public static void main(String args[]) {

    // here, n is declared inside of the for loop
    for(int n=10; n>0; n--)
      System.out.println("tick " + n);
  }
}
