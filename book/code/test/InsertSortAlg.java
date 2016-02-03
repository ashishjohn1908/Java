import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 29-Aug-2009
 * Time: 13:21:35
 * To change this template use File | Settings | File Templates.
 */
public class InsertSortAlg {

    static int[] A = {77, 99, 44, 55, 22, 88, 11, 0, 66, 33};


    public static void main(String[] args) {


        System.out.println("A before the sort: " + Arrays.toString(A));
        sortArray(A);
        System.out.println("A after the sort: " + Arrays.toString(A));
    }


    static boolean greaterThan(int a, int b) {
        return (a > b);
    }

    static void swap(int A[], int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    static void sortArray(int[] A) {
        int n = A.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (greaterThan(A[i], A[j])) {
                    swap(A, i, j);
                }
            }
        }
    }

}