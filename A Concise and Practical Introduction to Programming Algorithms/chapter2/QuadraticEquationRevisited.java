import java.util.Scanner;

class QuadraticEquationRevisited {
    public static void main(String[] arg) {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter a,b,c of equation ax^2+bx+c=0:");
        double a = keyboard.nextDouble();
        double b = keyboard.nextDouble();
        double c = keyboard.nextDouble();

        double delta = b * b - 4.0 * a * c;
        double root1, root2;

        if (delta >= 0) {
            root1 = (-b - Math.sqrt(delta)) / (2.0 * a);
            root2 = (-b + Math.sqrt(delta)) / (2.0 * a);
            System.out.println("Two real roots:" + root1 + " " + root2);
        } else {
            System.out.println("No real roots");
        }
    }
}