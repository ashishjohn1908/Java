package CoreMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:45:55
 * To change this template use File | Settings | File Templates.
 */
public class Interpolate {
    public double lagrange(double[][] valpairs, double xval) {
        int n = valpairs.length;
        double pn = 0.0;
        for (int i = 0; i < n; i++) {
            double px = 1;
            for (int j = 0; j < n; j++) {
                if (j != i)
                    px *= ((xval - valpairs[j][0]) / (valpairs[i][0] - valpairs[j][0]));
            }
            pn += px * valpairs[i][1];
        }


        return pn;

    }

    public double neville(double[][] valpairs, double xval)// interpolates for the given value xval//
    {
        // for positive values only  //
        double prec = 1e-2;
        int k = 0;
        int ky = 0;
        int n = valpairs.length;
        double[][] kpvals = new double[n][2];
        double x;
        double v;
        double nume;
        double denom;
        double newp = 0;
        double compareval;
        int counter = 0;
        int m = 0;
        int indx = 1;

        ArrayList<Double> pvalues = new ArrayList<Double>();
        TreeMap<Double, Double> h = new TreeMap<Double, Double>();// sorted//


        for (int i = 0; i < n; i++) {

            if (xval > valpairs[i][0]) {
                h.put(new Double(xval - (valpairs[i][0])), new Double(valpairs[i][1]));

            } else {
                h.put(new Double(Math.abs(xval - valpairs[i][0])), new Double(-valpairs[i][1]));// mark the negative terms//

            }

        }

        Iterator<Double> kee = h.keySet().iterator();
        Iterator<Double> val = h.values().iterator();

        while (val.hasNext()) {
            x = val.next().doubleValue();
            v = (x > 0.0) ? kee.next().doubleValue() - xval : xval + kee.next().doubleValue();// Reconstituting the values  //

            x = Math.abs(x);
            v = Math.abs(v);
            kpvals[k][0] = v;
            kpvals[k][1] = x;
            if (counter >= 1) {

                pvalues.add(ky, new Double(((((xval - kpvals[ky][0]) * kpvals[ky + 1][1]) + ((kpvals[ky + 1][0] - xval) * kpvals[ky][1])) / (kpvals[ky + 1][0] - kpvals[ky][0]))));
                double res = pvalues.get(ky).doubleValue();
                ky++;
            }
            counter++;
            k++;
        }

        while (!pvalues.isEmpty()) {
            indx++;
            compareval = pvalues.get(0).doubleValue();

            for (m = 0; m < pvalues.size() - 1; m++) {
                nume = (((xval - kpvals[m][0]) * (pvalues.get(m + 1).doubleValue())) + (kpvals[indx + m][0] - xval) * (pvalues.get(m).doubleValue()));
                denom = (kpvals[(indx + m)][0] - kpvals[m][0]);
                newp = nume / denom;
                pvalues.set(m, new Double(newp));
            }
            if ((Math.abs(compareval - newp)) < prec) {
                return compareval;
            }
            pvalues.remove(m);

        }
        return newp;

    }


    public double newtonDiffs(double[][] valpairs, double xval)// NEEDS RE-CHECKING
    {
        // for positive values only  provides an interpolation  for a given x value//
        double prec = 1e-2;
        int k = 0;
        int ky = 0;
        int inc = 0;
        int n = valpairs.length;
        double[][] kpvals = new double[n][2];
        double x;
        double v;
        double nume;
        double denom;
        double newp = 0;
        double compareval;
        int counter = 0;
        int m = 0;
        int indx = 1;
        ArrayList<Double> pvalues = new ArrayList<Double>();
        LinkedHashMap<Double, Double> h = new LinkedHashMap<Double, Double>();//sorting in order not required  //
        //TreeMap h=new TreeMap();
        for (int i = 0; i < n; i++) {

            h.put(new Double(valpairs[i][0]), new Double(valpairs[i][1]));
        }
        Iterator<Double> kee = h.keySet().iterator();
        Iterator<Double> val = h.values().iterator();

        while (val.hasNext()) {
            x = val.next().doubleValue();
            v = kee.next().doubleValue();
            System.out.println("values == " + x + "   key values ==  " + v);
            kpvals[k][0] = v;
            kpvals[k][1] = x;
            if (counter >= 1) {

                pvalues.add(ky, new Double(((kpvals[ky + 1][1]) - (kpvals[ky][1])) / (kpvals[ky + 1][0] - kpvals[ky][0])));
                double res = pvalues.get(ky).doubleValue();
                ky++;
            }
            counter++;
            k++;
        }

        while (!pvalues.isEmpty()) {
            indx++;
            compareval = pvalues.get(0).doubleValue();
            for (m = 0; m < pvalues.size() - 1; m++) {
                nume = ((pvalues.get(m + 1).doubleValue()) - (pvalues.get(m).doubleValue()));
                denom = (kpvals[(indx + m)][0] - kpvals[m][0]);// divided differences//
                newp = nume / denom;
                System.out.println("Divided differences  ==  " + newp);
                pvalues.set(m, new Double(newp));

            }
            //if ((Math.abs(compareval-newp))>0.00000001)
            //	if((Math.abs(compareval-newp))<prec)// or some predetermined evaluation/degree criteria//
            //{

            //	return compareval;
            //}
            pvalues.remove(m);

        }

        return newp;
    }


}

