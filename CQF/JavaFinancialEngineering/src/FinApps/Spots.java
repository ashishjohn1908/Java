package FinApps;

import static java.lang.Math.exp;
import static java.lang.Math.log;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 21:18:02
 * To change this template use File | Settings | File Templates.
 */
public class Spots {

    /**
     * Creates a new instance of Spots
     */
    public Spots() {
    }

    public double[] spotFcoupon(double[][] pcdata) {
        int n = pcdata.length;
        double[] spots = new double[n];
        double price;
        int s = 0;
        double indx = 1.0;
        spots[0] = ((100.0 / pcdata[0][0]) - 1);//bootstrapping start
        price = (pcdata[0][0] / 100.0);
        for (s = 1; s < n; s++) {
            indx++;
            spots[s] = (exp(1 / indx * log((pcdata[s][1] + 100.0) / (pcdata[s][0] - (pcdata[s][1] * price)))) - 1);
            price += (exp(-indx * log(1 + spots[s])));

        }
        return spots;
    }

    public double[] spotFcoupon(double[][] pcdata, int periods)// for  period frequency of annual coupons
    {
        int n = pcdata.length;
        double[] spots = new double[n];
        double price;
        double temp = 0;
        int s = 0;
        double indx = 1.0;
        spots[0] = ((100.0 / pcdata[0][0]) - 1);
        price = (pcdata[0][0] / 100.0);/* first entry */
        for (s = 1; s < n; s++) {
            indx++;
            spots[s] = (exp(1 / indx * log(((pcdata[s][1] / periods) + 100.0) / (pcdata[s][0] - ((pcdata[s][1] / periods) * price)))) - 1);
            price += (exp(-indx * log(1 + spots[s])));
        }
        return spots;
    }

    /* returns the n period coupon for par price given the spot rate --produces par yield curve*/
    public double parCoupon(double[] spots, int nperiod)// for single point data. Assumes spots is linear for the nperiod
    {
        int i = spots.length;
        int j = 0;
        int counter = 0;
        double flowdisc = 0.0;
        double finaldisc = 0.0;
        if (nperiod > i) {

            return -1.0;
        }

        finaldisc = (1.0 - (exp(-nperiod * log(1.0 + spots[(nperiod - 1)]))));
        System.out.println("FINAL DISCOUNT IN 1 ==" + finaldisc);
        for (double d : spots) {
            if (j < nperiod) {
                j++;
                flowdisc += ((exp(-j * log(1.0 + d))));

            }
        }
        return (finaldisc / flowdisc);
    }

    public double[] parCoupon(double[][] spots)// Arbitrary input data treated as double types
    {
        double nperiod = 0.0;
        double flowdisc = 0.0;
        double finaldisc = 0.0;
        double[] returnvalues = new double[spots.length];

        for (int k = 0; k < (spots.length); k++) {
            nperiod = spots[k][0];
            finaldisc = (1.0 - (exp(-nperiod * log(1.0 + spots[k][1]))));
            flowdisc += ((exp(-spots[k][0] * log(1.0 + spots[k][1]))));

            returnvalues[k] = (finaldisc / flowdisc);
        }
        return returnvalues;
    }
}
