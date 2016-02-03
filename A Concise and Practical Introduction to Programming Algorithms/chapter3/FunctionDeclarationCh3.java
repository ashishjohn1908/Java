class FunctionDeclarationCh3 {

    static int square(int x) {
        return x * x;
    }

    static boolean isOdd(int p) {
        if ((p % 2) == 0)
            return false;
        else
            return true;
    }

    static double distance(double x, double y) {
        double result;
        if (x > y) result = x - y;
        else result = y - x;
        return result;
    }

    /*	static double distance(double x, double y)
                         {double result;
                         if (x>y) result=x-y;
                                  else return y-x;}
                                  */


    static void display(double x, double y) {
        System.out.println("(" + x + "," + y + ")");
        return; // return void
    }

    public static void main(String[] args) {
        FunctionDeclarationCh3.display(3, 2);
        display(square(2), distance(5, 9));

        int p = 123124345;
        if (isOdd(p))
            System.out.println("p is odd");
        else
            System.out.println("p is even");

        System.out.println(FunctionStack.F(1.3));
    }
}