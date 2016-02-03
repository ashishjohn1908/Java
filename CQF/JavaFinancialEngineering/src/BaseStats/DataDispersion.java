package BaseStats;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:18:11
 * To change this template use File | Settings | File Templates.
 */

public final class DataDispersion {
    private static double rmean;
    private static double rvariance;
    private static double rsum;

    public static double DgetMean() {
        return rmean;
    }

    public static double DgetVariance() {
        return rvariance;
    }

    public static double DgetSum() {
        return rsum;
    }

    public static double mean(double[] x)//arithmetic mean for a single list//
    {
        double total = 0.0;
        for (int i = 0; i < x.length; i++)
            total += x[i];
        return total / x.length;
    }

    public static double[] dumean(double[][] x)//arithmetic mean for a double list//
    {
        double x1 = 0.0;
        double y = 0.0;
        double[] total = new double[2];
        for (int i = 0; i < x.length; i++) {
            x1 += x[i][0];
            y += x[i][1];

        }
        total[0] = x1 / x.length;
        total[1] = y / x.length;
        return total;
    }

    public static double[] dumean(Object[] x, Object[] y)//arithmetic mean for a double list/of Object/
    {
        Double xc;
        Double yc;
        double x1 = 0.0;
        double y1 = 0.0;
        double[] total = new double[2];
        for (int i = 0; i < x.length; i++) {
            xc = (Double) x[i];
            x1 += xc.doubleValue();
            yc = (Double) y[i];
            y1 += yc.doubleValue();

        }
        total[0] = x1 / x.length;
        total[1] = y1 / y.length;
        return total;
    }

    public static double convmean(double[] x)// for large length//
    {
        double total = 0.0;
        for (int i = 0; i < x.length; i++)
            total += x[i];
        return total / (x.length - 1);
    }

    public static double mean(double[][] x) //returns expected value for variable * probability//
    {

        double total = 0.0;
        double probability = 0.0;


        for (int i = 0; i < x.length; i++)//the number of rows//
        {
            total += (x[i][0] * x[i][1]);
            probability += x[i][1];
        }
        if (probability != 1.0)
            //throw new RuntimeException//
            System.out.println("WARNING ! The probabilities do not sum to 1.0");
        return total;


    }

    public static double[] variances(double[][] v1)//variance of a single variable with equal likeliehood for double inputs//
    {
        double[] output = new double[2];
        double sumd = 0.0;
        double sumd1 = 0.0;
        double total = 0.0;
        double total1 = 0.0;
        for (int i = 0; i < v1.length; i++) {
            total += v1[i][0];
            total1 += v1[i][1];

            sumd += Math.pow(v1[i][0], 2);//sum of x sqrd
            sumd1 += Math.pow(v1[i][1], 2);//sum of x sqrd
        }
        System.out.println("sumdiffs............" + sumd);
        System.out.println("sumdiffs............" + sumd1);
        total = (Math.pow(total, 2) / v1.length);//sum of [x]sqrd/n
        total1 = (Math.pow(total1, 2) / v1.length);//sum of [x]sqrd/n
        System.out.println("TOTAL............" + total);
        System.out.println("TOTAL............" + total1);
        System.out.println("....The length......" + v1.length);
        System.out.println("....VARIANCE OUT....." + ((sumd - total) / ((v1.length) - 1)));
        System.out.println("....VARIANCE2 OUT....." + ((sumd1 - total1) / ((v1.length) - 1)));
        output[0] = ((sumd - total) / ((v1.length) - 1));
        output[1] = ((sumd1 - total1) / ((v1.length) - 1));
        //return (sumd-total)/((v1.length)-1);	//true value of convergence as length is large//
        //return (sumd-total)/((v1.length)-1);	//true value of convergence as length is large//
        return output;

    }

    public static double[] variances(Object[] v1, Object[] v2)//variance of a single variable with equal likeliehood for double  Object nputs//
    {
        Double t1;
        Double t1c;
        double[] output = new double[2];
        double sumd = 0.0;
        double sumd1 = 0.0;
        double total = 0.0;
        double total1 = 0.0;
        for (int i = 0; i < v1.length; i++) {
            t1 = (Double) v1[i];
            t1c = (Double) v2[i];
            total += t1.doubleValue();
            total1 += t1c.doubleValue();

            sumd += Math.pow(t1.doubleValue(), 2);//sum of x sqrd
            sumd1 += Math.pow(t1c.doubleValue(), 2);//sum of x sqrd
        }
        System.out.println("sumd............" + sumd);
        System.out.println("sumd1............" + sumd1);
        total = (Math.pow(total, 2) / v1.length);//sum of [x]sqrd/n
        total1 = (Math.pow(total1, 2) / v2.length);//sum of [x]sqrd/n
        System.out.println("TOTAL............" + total);
        System.out.println("TOTAL1............" + total1);
        System.out.println("....The length......" + v1.length);
        System.out.println("....VARIANCE OUT....." + ((sumd - total) / ((v1.length) - 1)));
        System.out.println("....VARIANCE2 OUT....." + ((sumd1 - total1) / ((v2.length) - 1)));
        output[0] = ((sumd - total) / ((v1.length) - 1));
        output[1] = ((sumd1 - total1) / ((v2.length) - 1));
        //return (sumd-total)/((v1.length)-1);	//true value of convergence as length is large//
        //return (sumd-total)/((v1.length)-1);	//true value of convergence as length is large//
        System.out.println("Variances output............" + output[0]);
        System.out.println("Variances output 2............" + output[1]);
        return output;

    }


    public static double variance(double[] v1)//variance of a single variable with equal likeliehood//
    {

        double sumd = 0.0;
        double total = 0.0;
        for (int i = 0; i < v1.length; i++) {
            total += v1[i];
            sumd += Math.pow(v1[i], 2);//sum of x sqrd
        }
        System.out.println("sumdiffs............" + sumd);
        rmean = (total / v1.length);
        System.out.println("SAMPLE MEAN............" + (total / v1.length));
        total = (Math.pow(total, 2) / v1.length);//sum of [x]sqrd/n
        System.out.println("TOTAL............" + total);
        System.out.println("....The length......" + v1.length);
        rvariance = ((sumd - total) / ((v1.length) - 1));
        System.out.println("....VARIANCE OUT....." + ((sumd - total) / ((v1.length) - 1)));
        return (sumd - total) / ((v1.length) - 1);    //true value of convergence as length is large//

    }

    public static double variance(double[][] v1)///variance of a variable with different probability of otcome//
    {

        double sumd = 0.0;
        double total = 0.0;
        double totalpow = 0.0;
        double probability = 0.0;
        for (int i = 0; i < v1.length; i++) {
            total += (v1[i][0] * v1[i][1]);//mean or expected value//
            totalpow += (Math.pow(v1[i][0], 2) * v1[i][1]);//E[X2]//
            probability += v1[i][1];
        }
        if (probability != 1.0)
            System.out.println("WARNING !The probabilities do not approximate to sum to 1.0");
        total = Math.pow(total, 2);
        System.out.println("TOTAL............" + total);
        System.out.println("....The length......" + v1.length);
        System.out.println("....VARIANCE OUT....." + (totalpow - total));
        return (totalpow - total);

    }

    public static double standardDeviation(double s1) {
        double sdev;
        return sdev = Math.sqrt(s1);
    }


    public static double covar(double[][] outcomes)//equally likely outcomes//
    {
        double sa = 0.0;
        double sb = 0.0;
        double product = 0.0;
        int size = outcomes.length;
        for (int i = 0; i < size; i++) {
            sa += outcomes[i][0];//x values or proprtions//
            sb += outcomes[i][1];//y values or proportions//
        }
        double samn = sa / size;//expected value of x//
        double sbmn = sb / size;//expected value of y//
        for (int i = 0; i < size; i++) {
            product += ((outcomes[i][0] - samn) * (outcomes[i][1] - sbmn));//sum of the products ofdeviations//
        }
        return product / size;//covariance//

    }

    public static double covar(Object[] outcomes, Object[] outcomes2)//equally likely outcomes for two Object arrays//
    {
        Double outa;
        Double outb;
        Double sac;
        Double sbc;
        double sa = 0.0;
        double sb = 0.0;
        double product = 0.0;
        int size = outcomes.length;
        for (int i = 0; i < size; i++) {
            sac = (Double) outcomes[i];
            sbc = (Double) outcomes2[i];

            sa += sac.doubleValue();//x values or proprtions//
            sb += sbc.doubleValue();//y values or proportions//

        }
        System.out.println("COVAR ARRAY VALUES  1ST:  " + sa + "    2nd:  " + sb);

        double samn = sa / size;//expected value of x//
        double sbmn = sb / size;//expected value of y//
        for (int i = 0; i < size; i++) {
            outa = (Double) outcomes[i];
            outb = (Double) outcomes2[i];

            product += ((outa.doubleValue() - samn) * (outb.doubleValue() - sbmn));//sum of the products ofdeviations//
            System.out.println("Product in the loop    :" + product + "  outa value, " + outa.doubleValue() + "   outb value" + outb.doubleValue());
        }
        return (product / size);//covariance//

    }

    public static double covar2(double[][] outcomexyp)//inputs of non equal joint outcomes//
    {
        // data in the form A value,  B value . Probability(P) of B and A the same//
        double productx = 0.0;
        double producty = 0.0;
        int size = outcomexyp.length;
        double covariance = 0.0;
        for (int i = 0; i < size; i++) {
            // A[n][0],B[n][1],P[n][2]..........//
            productx += outcomexyp[i][0] * outcomexyp[i][2];//probability * observed value//
            System.out.println("intermediate covar2   [i][0],: [i][2],:" + outcomexyp[i][0] + " " + outcomexyp[i][2]);
            producty += outcomexyp[i][1] * outcomexyp[i][2];
            System.out.println("intermediate covar2   [i][1],: [i][2],:" + outcomexyp[i][1] + " " + outcomexyp[i][2]);
            // productx+=sx;
            // producty+=sy;
        }

        for (int j = 0; j < size; j++) {
            double xdevs = outcomexyp[j][0] - productx;
            System.out.println("intermediate covar2   xdevs,:" + xdevs);
            double ydevs = outcomexyp[j][1] - producty;
            System.out.println("intermediate covar2   ydevs,:" + ydevs);
            double devproduct = xdevs * ydevs;
            System.out.println("intermediate covar2  product deviations,:" + devproduct);
            double covprobs = devproduct * outcomexyp[j][2];
            System.out.println("intermediate covar2   dviations product times probability,:" + covprobs);
            covariance += covprobs;
        }
        return covariance;
    }


    public static double correlation(double cov, double sd1, double sd2) {
        double cor = cov / sd1 * sd2;
        return cor;
    }
}
