import java.util.Scanner;

class KeyboardIntInputNew {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int val;

        System.out.print("Enter an integer please:");
        val = keyboard.nextInt();
        System.out.print("I read the following value:");
        System.out.println(val);
    }


}