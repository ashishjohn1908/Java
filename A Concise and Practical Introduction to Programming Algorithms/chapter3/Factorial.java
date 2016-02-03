class Toolbox {

    static int factorial(int n) {
        int result = 1;

       // while (n > 0) {
       //     result *= n; // similar to result=result*n;
       //     n--; // or equivalently --n
       // }

        for(int i =0; n > i; n--){
            result *= n;
        }

        return result; // Factorial n
    }
}


class ExampleFactorial {

    public static void main(String[] args) {
        System.out.println("4!=" + Toolbox.factorial(4));
    }
}