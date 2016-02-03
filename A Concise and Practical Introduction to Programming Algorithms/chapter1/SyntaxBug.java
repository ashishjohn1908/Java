class SyntaxBug {

    public static void main(String[] args) {
        /*
         *Ask for a value
         */

        double val, fval;
        val = new Double(args[0]);
        fval=3*val*val+2;
        System.out.println("f(x)="+val);
    }

}