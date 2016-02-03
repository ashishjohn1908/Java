package FinApps.examples;

import CoreMath.Inversenorm;
import FinApps.Lnormprice;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 22:56:05
 * To change this template use File | Settings | File Templates.
 */
public class Examplelognorm_1 {

    public Examplelognorm_1() {
    }

    public static void main(String[] args) {
        double[] rangevalues = new double[2];
        double[] rets = new double[2];
        Inversenorm inv = new Inversenorm();
        double conflevel = inv.InverseNormal(0.90);// get the x factor
//for the chosen
//confidence level
        Lnormprice l = new Lnormprice(conflevel);
        l.logprice(51.0, 53.50, 0.11, 0.19, 0.25);
        l.returnrate(0.11, 0.19, 1.0);
        rangevalues = l.getRange();
        rets = l.getRetrange();
        System.out.println("lnormices" + l.getAverage() + "sd=="
                + l.getSd() + "pDF==" + l.getPdf() + "cdf==" + l.getCdf());
        System.out.println(" RANGE low =" + rangevalues[0]
                + " HIGHER==" + rangevalues[1]);
        System.out.println(" RET RANGE low =" + rets[0]
                + " RET HIGHER==" + rets[1]);
    }

}
