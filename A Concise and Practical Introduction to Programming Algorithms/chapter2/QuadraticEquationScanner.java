import java.util.Locale;
import java.util.Scanner;

class QuadraticEquationScanner {
    public static void main(String[] arg) {
        double a, b, c; // choose a=1, b=1, c=1
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US);
        a = input.nextDouble();
        b = input.nextDouble();
        c = input.nextDouble();
        double delta = b * b - 4.0 * a * c;
        double root1, root2;

/*
// BEWARE: potentially Not a Number (NaN) for neg. discriminant!
root1= (-b-Math.sqrt(delta))/(2.0*a);
root2= (-b+Math.sqrt(delta))/(2.0*a);
*/
        if (delta >= 0.0d) {
            root1 = (-b - Math.sqrt(delta)) / (2.0 * a);
            root2 = (-b + Math.sqrt(delta)) / (2.0 * a);
            System.out.println("root1=" + root1 + " root2=" + root2);
        } else {
            System.out.println("Imaginary roots!");
        }
    }
}


