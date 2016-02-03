class ArrayInFunction {

    public static void MyFunction(int n) {
        int array[] = new int[n];
        int i;
        InformationArray(array);
    }


    public static void InformationArray(int[] t) {
        System.out.println("Size of array given in argument is:" + t.length);
    }

    public static void main(String[] args) {

        MyFunction(2312);
        MyFunction(2008);
        int x[] = new int[12];
    }
}