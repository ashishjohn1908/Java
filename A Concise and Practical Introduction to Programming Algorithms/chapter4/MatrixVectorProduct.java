class MatrixVectorProduct {
    static int[] MultiplyMatrixVector(int[][] mat, int[] v) {
        int[] result;
        result = new int[mat.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
            for (int j = 0; j < v.length; j++)
                result[i] += mat[i][j] * v[j];
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] M = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] v = {1, 2, 3};
        int[] z = MultiplyMatrixVector(M, v);

        for (int i = 0; i < z.length; i++)
            System.out.print(z[i] + " ");
    }

}