import java.util.Scanner;

class SmallProg {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int val;


        System.out.print("Enter an integer please:");
        val = keyboard.nextInt();
        val *= val; // squared the input value
        System.out.println("Squared value=" + val);
    }

}