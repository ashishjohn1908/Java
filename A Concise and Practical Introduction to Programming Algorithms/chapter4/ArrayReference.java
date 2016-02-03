class ArrayReference {

    public static void main(String[] args) {
        int[] v = {0, 1, 2, 3, 4};

        System.out.println("Reference of array u in memory:" + v);
        System.out.println("Value of the 3rd element of array v:" + v[2]);

        // Declare a new array and assign its reference to the reference of array v
        int[] t = v;
        System.out.println("Reference of array v in memory:" + v);// same as u

        System.out.println(v[2]);
        t[2]++;
        System.out.println(v[2]);
        v[2]++;
        System.out.println(t[2]);
    }
}