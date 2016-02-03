class MultidimArrays {

    static void f2D(double[][] tab) {
        System.out.println("Number of lines:" + tab.length);
        System.out.println("Number of columns:" + tab[0].length);
    }

    static void f3D(double[][][] tab) {
        System.out.println("Number of lines X:" + tab.length);
        System.out.println("Number of columns Y:" + tab[0].length);
        System.out.println("Number of depths Z:" + tab[0][0].length);
    }


    public static void main(String[] args) {
        double[][] var = new double[3][4];
        f2D(var);

        double[][][] tmp = new double[4][5][7];
        f3D(tmp);
    }

}