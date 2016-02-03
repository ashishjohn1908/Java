class VectorInnerProduct {

    static double innerproduct(int[] x, int[] y) {
        double sum = 0.0;
        System.out.println("Dim of vector x:" + x.length + " Dim of vector y:" + y.length);

        for (int i = 0; i < x.length; ++i)
            sum = sum + x[i] * y[i];
        return sum;
    }

    public static void main(String[] args) {
        int dimension = 30;
        int[] v1, v2;

        v1 = new int[dimension];
        v2 = new int[dimension];

        for (int i = 0; i < dimension; i++) {
            v1[i] = (int) (Math.random() * 100);// random int [0,99]
            v2[i] = (int) (Math.random() * 100);// random int [0,99]
        }
        System.out.println("The inner product of v1 and v2 is " + innerproduct(v1, v2));
    }
}