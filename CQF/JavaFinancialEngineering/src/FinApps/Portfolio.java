package FinApps;

import BaseStats.DataDispersion;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:57:34
 * To change this template use File | Settings | File Templates.
 */
public class Portfolio

{
    double xx;
    double yy;
    double zz;

    private int numbersecs;
    private int datanumber;
    private int offsetnumber;
    private ArrayList<Double> riskdata = new ArrayList<Double>();
    private ArrayList<Double> rawdata = new ArrayList<Double>();
    private ArrayList<Object> folioentry = new ArrayList<Object>();

    private ArrayList<Double> getRisk() {
        return riskdata;
    }

    private ArrayList<Double> getRaw() {
        return rawdata;
    }

    private ArrayList<Object> getFolio() {
        return folioentry;
    }

    private int getEntrynums() {
        return numbersecs;
    }

    private int getDatalength() {
        return datanumber;
    }

    private int getOffset() {
        return offsetnumber;
    }

    public void folioprop(String sname, int numshares, double initialprice) {
        double values;

        ArrayList<Object> folioentry = getFolio();

        folioentry.add(sname);    //wrapper classes//
        folioentry.add(new Integer(numshares));
        folioentry.add(new Double(initialprice));


    }


    public void folioendvals(String sname, int numshares, double initialprice, double expectedpr)//  for present share prices//
    {
        ArrayList<Object> folioentry = getFolio();
        folioentry.add(sname);
        folioentry.add(new Integer(numshares));
        folioentry.add(new Double(initialprice));    //wrapper classes//
        folioentry.add(new Double(expectedpr));
    }

    public void folioreturns(String sname, int numshares, double initialprice, double expectedrets)//  for expected end period share price//
    {
        ArrayList<Object> folioentry = getFolio();
        folioentry.add(sname);//0 entry....index number//
        folioentry.add(new Integer(numshares));
        folioentry.add(new Double(initialprice));
        folioentry.add(new Double(expectedrets));//3 entry//

    }

    public void insertdata(double data)//allows insertion of data as single entry.//
    {
        ArrayList<Double> rawdata = getRaw();

        rawdata.add(new Double(data));// takes raw expected values //

    }

    public void insertdata(double[] data)//allows insertion of data as an array.//
    {
        ArrayList<Double> rawdata = getRaw();
        for (int i = 0; i < data.length; i++) {
            double prod = data[i];
            rawdata.add(new Double(prod));// takes raw expected values //
        }
    }

    public void insertdata(double[][] data)// takes raw  values and associated probabilities//
    {
        ArrayList<Double> rawdata = getRaw();
        for (int i = 0; i < data.length; i++) {
            double prod = data[i][0] * data[i][1];
            rawdata.add(new Double(prod));
        }
    }

    public void insertstring(String name)//allows insertion of data only.//
    {
        ArrayList<Object> folioentry = getFolio();
        folioentry.add(name);

    }

    public void propanalysis() {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(10);
        formatter.setMinimumFractionDigits(10);
        ArrayList<Double> riskdata = getRisk();
        ArrayList<Object> folioentry = getFolio();
        ArrayList<Double> rawdata = getRaw();
        int blocksize = getDatalength();
        int nos = getEntrynums();
        double[] compare = new double[blocksize];
        double[] codata = new double[blocksize];
        double[][] covalues = new double[blocksize][2];
        int size = rawdata.size();
        double riskvalue = 0.0;
        Double er;
        Double covars;
        int a = 0;
        int comp = 0;
        int end = 0;
        int gets = 0;

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("c:\\portsriskR2.doc"), true);
            PrintWriter w = new PrintWriter(new FileWriter("c:\\ports3riskR2.doc"), true);

            while (end < nos) {

                for (a = 0; a < blocksize; a++) {
                    Double value = rawdata.get(comp);
                    compare[a] = value.doubleValue();
                    covalues[a][0] = value.doubleValue();
                    w.print("input data  : " + compare[a]);
                    comp++;

                }
                w.println(" ");

                for (int counter = 0; counter < size;)// for length of the data set//
                {
                    for (int b = 0; b < blocksize; b++)//for each security entry//
                    {

                        Double covalue = rawdata.get(counter);
                        codata[b] = covalue.doubleValue();
                        covalues[b][1] = covalue.doubleValue();
                        counter++;

                    }


                    double cors = DataDispersion.covar(covalues);
                    double[] answer2 = DataDispersion.variances(covalues);
                    double[] meanvals = DataDispersion.dumean(covalues);
                    System.out.println("  COVARIANCE :   " + cors);
                    System.out.println(" : variance 1  " + answer2[0] + "     variance 2 " + answer2[1]);
                    System.out.println("  MEAN 1 :   " + meanvals[0] + "   MEAN 2:   " + meanvals[1]);
                    riskdata.add(new Double(meanvals[0] * meanvals[1] * cors));
                    pw.println("   " + meanvals[0] + " *  " + meanvals[1] + " *  " + cors + "   =   " + (meanvals[0] * meanvals[1] * cors));
                    //er=(Double)riskdata.get(gets);
                    //double tempout=er.doubleValue();
                    //gets++;
                    //	System.out.println("  THE PRODUCT OF X1*X2*S12=     "+tempout);
                }
                end++;
            }
            for (int d = 0; d < riskdata.size(); d++) {
                er = riskdata.get(d);
                double tempout = er.doubleValue();
                riskvalue += tempout;

                System.out.println("  THE PRODUCT OF X1*X2*S12=     " + tempout);


            }
            riskvalue = Math.sqrt(riskvalue);

            System.out.println("  THE PORTFOLIO RISK IS=     " + riskvalue);
            pw.println(" Portfolio Standard Deviation:   " + riskvalue);
            pw.close();
            w.close();
        }
        catch (IOException foe) {
            System.out.println(foe);
        }
    }

    public void retanalysis() {
        System.out.println("THE EXPECTED RETANANALYSIS:     ");
        ArrayList<Object> folioentry = getFolio();
        int arraysize = folioentry.size();
        int blocksize = getDatalength();
        double[] inputdata = new double[blocksize];
        int offset = getOffset();
        int i = 0;
        System.out.println("THE RETANALYSIS DATA arraysize" + arraysize + " the blocksize:   " + blocksize);

        while (i < arraysize) {
            i += offset;
            for (int k = 0; k < blocksize; k++) {

                Double totals = (Double) folioentry.get(i);
                System.out.println("THE i variable in the loop:     " + i + "   k variable   " + k);
                inputdata[k] = totals.doubleValue();
                i++;
            }
            double d = DataDispersion.variance(inputdata);
            System.out.println("Mean values for the securities    " + DataDispersion.mean(inputdata));
            System.out.println("Variance for the securities    " + DataDispersion.variance(inputdata));
            System.out.println("SD values for the securities    " + DataDispersion.standardDeviation(d));
        }
        folioentry.clear();
    }

    public void insertnumsec(int size) {
        numbersecs = size;
    }

    public void datasize(int size)//get the block size of list data//
    {
        datanumber = size;
    }

    public void offsetsize(int size) {
        offsetnumber = size;
    }

    public void retInitprice() {
        double tots = 0.0;
        double proportion = 0.0;
        double initialportval = 0.0;
        double totalinvest = 0.0;
        double portfolioreturn = 0.0;
        ArrayList<Object> folioentry = getFolio();
        final int collectionsize = folioentry.size();//get the size of the array//


        for (int i = 3; i < collectionsize;) {
            System.out.println(" TESTING FOR collectionsize    " + collectionsize);

            Double totals = (Double) folioentry.get(i - 1);//Initial market price//
            Integer totalnums = (Integer) folioentry.get(i - 2);//number of shares//
            initialportval += (totals.doubleValue() * totalnums.intValue());
            //System.out.println(" TESTING FOR initial total investment     "+initialportval);
            //System.out.println(" TESTING FOR loop counter i     "+i);
            //pw.println(" TESTING FOR collectionsize    "+collectionsize+"initialinvest "+initialportval);
            i = i + 4;
        }
        for (int j = 3; j < collectionsize;) {
            Double sums = (Double) folioentry.get(j);//Expected returns//
            Double totalsinitial = (Double) folioentry.get(j - 1);//Initial market price//
            Integer totalnumsinitial = (Integer) folioentry.get(j - 2);//number of shares//
            String security = (String) folioentry.get(j - 3);//name of security//
            tots = (totalsinitial.doubleValue() * totalnumsinitial.intValue());//total investment per share//
            proportion = tots / initialportval;//as a proportion of the initial porfolio valuation//
            totalinvest = sums.doubleValue() * proportion;//expected return (%) * proportion//
            portfolioreturn += totalinvest;// Gross portfolio expected return//
            j = j + 4;
            System.out.println(" Proportion of initial mkt value   :" + proportion);


        }
        //System.out.println(" Start Period Valuation of Portfolio   :"+initialportval);
        //System.out.println("Expected Valuation of Portfolio Return   :"+portfolioreturn);

        folioentry.clear();


    }


    public void retendvals() {
        double sums = 0.0;
        double expectedendval = 0.0;
        double startval = 0.0;
        ArrayList<Object> folioentry = getFolio();
        final int collectionsize = folioentry.size();//get the size of the array//
        for (int i = 3; i < collectionsize;) {
            Double initialval = (Double) folioentry.get(i - 1);
            Integer numshares = (Integer) folioentry.get(i - 2);
            double suminitial = (initialval.doubleValue() * numshares.intValue());//number of shares * initial market price of share//
            startval += suminitial;//start value of the portfolio//
            Double expct = (Double) folioentry.get(i);//expected values//
            sums = (expct.doubleValue() * numshares.intValue());//number of shares * end period expected values//
            expectedendval += sums;//Expected end period value of portfolio//
            i = i + 4;
        }
        System.out.println(" Start Period Valuation of Portfolio   :" + startval);
        System.out.println("Expected End Period Valuation of Portfolio   :" + expectedendval);
        System.out.println("Expected Return of Porfolio    :" + ((expectedendval - startval) / startval));
        folioentry.clear();
    }


}
