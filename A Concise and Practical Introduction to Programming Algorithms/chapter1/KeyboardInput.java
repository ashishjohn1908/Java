import java.util.Locale;
import java.util.Scanner;

class KeyboardIntInput {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        keyboard.useLocale(Locale.UK);

        int val;
        double vald;
        double valf;

        System.out.print("Enter an integer please:");
        val = keyboard.nextInt();
        System.out.print("I read the following value:");
        System.out.println(val);

        System.out.print("Enter a float please:");
        valf = keyboard.nextFloat();
        System.out.print("I read the following value:");
        System.out.println(valf);

        System.out.print("Enter an double please:");
        vald = keyboard.nextDouble();
        System.out.print("I read the following value:");
        System.out.println(vald);

    }


}