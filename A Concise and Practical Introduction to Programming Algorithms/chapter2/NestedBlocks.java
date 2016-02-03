class NestedBlock {
    public static void main(String[] arg) {

        boolean x = false;

        if (x = true) System.out.println("x is true");
        else System.out.println("x is false");

        int i = 3;
        int j = 4;

        System.out.println("i=" + i + " j=" + j);

        {
            // Cannot redefine a variable i here
            int ii = 5;
            j++;
            i--;
        }

        System.out.println("i=" + i + " j=" + j);
// Cannot access variable ii here			 		
    }
}