import org.apache.commons.math3.stat.descriptive.moment.VectorialCovariance;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.RealMatrix;


import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 13-Jul-2009
 * Time: 19:09:03
 * To change this template use File | Settings | File Templates.
 */
public class CovarTest {

    public static void main(String[] args) {
        VectorialCovariance covariance = new VectorialCovariance(4, true);
        double[] vect1 = new double[]{1, 3, 5, 7};
        double[] vect2 = new double[]{2, 5, 6, 8};
        try {
            covariance.increment(vect1);
            covariance.increment(vect2);
            RealMatrix matrix = covariance.getResult();
            matrix.transpose();
            double[][] doubles = matrix.getData();

            for (int i = 0; i < doubles.length; i++) {

                System.out.println("");
                for (int j = 0; j < doubles[i].length; j++) {
                    System.out.print(new BigDecimal(doubles[i][j]).setScale(2, 5) + " ");
                }
            }

        } catch (DimensionMismatchException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
