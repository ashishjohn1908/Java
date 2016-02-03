/**
 * Created by IntelliJ IDEA.
 * Author: Plamen Stilyianov
 * Title: MSc Financial Markets with Information Systems
 * Date: 01-Sep-2005
 * Time: 22:02:08
 * To change this template use File | Settings | File Templates.
 */
public class GeometricModels {

    //greeks
    public double delta, gamma, vega, rho, theta = 0.0;
   public double valueKemnaVorst, valueCurran = 0.0;

    public GeometricModels(double S, double K, double r, double div, double sig, double T) {
        valueKemnaVorst = modelKemnaVorstAsian(S, K, r, div, sig, T);
        valueCurran = modelCurranAsian(S, K, r, div, sig, T);
        delta = deltaGeomethric(S, K, T, r, div, sig);
        gamma = gammaGeomethric(S, K, T, r, div, sig);
        theta = thetaGeomethric(S, K, T, r, div, sig) / 365;
        vega = vegaGeomethric(S, K, T, r, div, sig) / 100;
        rho = rhoGeomethric(S, K, T, r, div, sig) / 100;
    }

    private double modelKemnaVorstAsian(double S, double K, double r, double div, double sig, double T) {
        double valueKemnaVorst;

        double b;   // cost of carry rate
        double siga;    // adjusted volatility
        double d1, d2;
        double t = 0.0;

        b = 0.5 * (r - div - Math.pow(sig, 2) / 6);

        siga = sig / Math.sqrt(3);

        d1 = (Math.log(S / K) + (b + 0.5 * Math.pow(siga, 2)) * T) / (siga * Math.sqrt(T));

        d2 = (Math.log(S / K) + (b - 0.5 * Math.pow(siga, 2)) * T) / (siga * Math.sqrt(T));

        valueKemnaVorst = S * Math.exp((b - r) * (T - t)) * CND(d1) - K * Math.exp(-r * (T - t)) * CND(d2);

        return valueKemnaVorst;
    }

    private double modelCurranAsian(double S, double K, double r, double div, double sig, double T) {

        double n = 260;
        double t1 = 0;
        double dt = T / n;
        double mu = Math.log(S) + ((r - div) - (0.5 * Math.pow(sig, 2))) * (t1 * dt + (n - 1) * 0.5 * dt);
        double vx = sig * Math.sqrt(t1 * dt + dt * (n - 1) * (2 * n - 1) / (6 * n));

        double curran;
        double K1;
        double d1;
        double[] ti = new double[(int) n + 1];
        double[] vi = new double[(int) n + 1];
        double[] mui = new double[(int) n + 1];
        double[] vxi = new double[(int) n + 1];

        for (int i = 1; i <= n; i++) {
            ti[i] = (i + t1) * dt;
            vi[i] = Math.sqrt(sig * sig * (t1 * dt + (i - 1) * dt));
            mui[i] = Math.log(S) + (r - div - 0.5 * sig * sig) * ti[i];
            vxi[i] = sig * sig * (t1 * dt + dt * ((i - 1) - i * (i - 1) / (2 * n)));
        }

        double summ2 = 0.0;

        for (int i = 1; i <= n; i++) {
            summ2 = summ2 + (1 / n) * Math.exp(mui[i] + (vxi[i] / (Math.pow(vx, 2))) * (Math.log(K) - mu)
                    + 0.5 * (Math.pow(vx, 2) + (Math.pow(vxi[i], 2) / Math.pow(vx, 2))));
        }

        K1 = 2 * K - summ2;
        d1 = (mu - Math.log(K1)) / vx;

        double summ1 = 0;

        for (int i = 1; i <= n; i++) {
            summ1 = summ1 + Math.exp(mui[i] + 0.5 * (Math.pow(vi[i], 2))) * CND(d1 + vxi[i] / vx);
        }

        curran = Math.exp(-r * T) * ((1 / n) * summ1 - K * CND(d1));
        return curran;
    }

    private double CND(double X) {
        double L, K, w;
        double a1 = 0.31938153, a2 = -0.356563782, a3 = 1.781477937, a4 = -1.821255978, a5 = 1.330274429;

        L = Math.abs(X);
        K = 1.0 / (1.0 + 0.2316419 * L);
        w = 1.0 - 1.0 / Math.sqrt(2.0 * Math.PI) * Math.exp(-L * L / 2) * (a1 * K + a2 * K * K + a3 * Math.pow(K, 3)
                + a4 * Math.pow(K, 4) + a5 * Math.pow(K, 5));

        if (X < 0.0) {
            w = 1.0 - w;
        }
        return w;
    }

    private double ND(double X) {
        double w;

        w = Math.exp(Math.pow(-X, 2) / 2) / Math.sqrt(2 * Math.PI);

        return w;
    }

    // GREEKS

    private double deltaGeomethric(double S, double K, double T, double r, double div, double sig) {
        double deltaGr;
        double ba;
        double b = r - div;
        double va;
        double d1;


        ba = 0.5 * (b + Math.pow(sig, 2) / 6);
        va = sig / Math.sqrt(3);

        d1 = (Math.log(S / K) + (ba + Math.pow(va, 2) / 2) * T) / (va * Math.sqrt(T));

        deltaGr = Math.exp((ba - r) * T) * CND(d1);

        return deltaGr;
    }

    private double gammaGeomethric(double S, double K, double T, double r, double div, double sig) {
        double gammaGr;
        double ba;
        double b = r - div;
        double va;
        double d1;


        ba = 0.5 * (b + Math.pow(sig, 2) / 6);
        va = sig / Math.sqrt(3);

        d1 = (Math.log(S / K) + (ba + Math.pow(va, 2) / 2) * T) / (va * Math.sqrt(T));

        gammaGr = Math.exp((ba - r) * T) * CND(d1) / (S * va * Math.sqrt(T));

        return gammaGr;
    }

    private double thetaGeomethric(double S, double K, double T, double r, double div, double sig) {
        double thetaGr;
        double ba;
        double b = r - div;
        double va;
        double d1, d2;


        ba = 0.5 * (b + Math.pow(sig, 2) / 6);
        va = sig / Math.sqrt(3);

        d1 = (Math.log(S / K) + (ba + Math.pow(va, 2) / 2) * T) / (va * Math.sqrt(T));
        d2 = d1 - (sig * Math.sqrt(T));

        thetaGr = -(S * Math.exp((ba - r) * T) * ND(d1) * va) / (2 * Math.sqrt(T)) - ((ba - r) * S * CND(d1)
                                            * Math.exp((ba - r) * T)) - (r * K * Math.exp(-r * T) * CND(d2));

        return thetaGr;
    }

    private double vegaGeomethric(double S, double K, double T, double r, double div, double sig) {
        double vegaGr;
        double ba;
        //noinspection UnusedAssignment
        ba = 0.0;
        double b = r - div;
        double va;
        double d1;


        ba = 0.5 * (b + Math.pow(sig, 2) / 6);
        va = sig / Math.sqrt(3);

        d1 = (Math.log(S / K) + (ba + Math.pow(va, 2) / 2) * T) / (va * Math.sqrt(T));
        // d2 = d1 - (sig*Math.sqrt(T));

        vegaGr = S * Math.exp((ba - r) * T) * ND(d1) * Math.sqrt(T);

        return vegaGr;
    }

    private double rhoGeomethric(double S, double K, double T, double r, double div, double sig) {
        double rhoGr;
        double ba;
        double b = r - div;
        double va;
        double d1, d2;


        ba = 0.5 * (b + Math.pow(sig, 2) / 6);
        va = sig / Math.sqrt(3);

        d1 = (Math.log(S / K) + (ba + Math.pow(va, 2) / 2) * T) / (va * Math.sqrt(T));
        d2 = d1 - (sig * Math.sqrt(T));

        if (b != 0)
            rhoGr = T * K * Math.exp(-r * T) * CND(d2);
        else {
            rhoGr = -T * new GenBlackScholesModels(S, K, r, div, sig, T).valueBlackScholes;
        }

        return rhoGr;
    }


    public static void main(String [] args) {

        //initial parameters
        //   double S = 4493;    // Stock price
        //   double K = 4493;    // strike price
        //   double T = 0.24658;      // Maturity date
        //   double sig = 0.21097;  // Volatility
        //   double r = 0.04314;   // Interest rate
        //   double div = 0.0087; // Divident yield

        double S = 35.75;    // Stock price
        double K = 35.75;    // strike price
        double T = 0.24658;      // Maturity date
        double sig = 0.26354;  // Volatility
        double r = 0.04271;   // Interest rate
        double div = 0.0457; // Divident yield

        GeometricModels gmTest = new GeometricModels(S, K, r, div, sig, T);

        System.out.println("curranOptionValue: " + gmTest.valueCurran);
        System.out.println("kemnaVorstOptionValue: " + gmTest.valueKemnaVorst);
        System.out.println("geometricDelta: " + gmTest.delta);
        System.out.println("geometricGamma: " + gmTest.gamma);
        System.out.println("geometricTheta: " + gmTest.theta);
        System.out.println("geometricVega: " + gmTest.vega);
        System.out.println("geometricRho: " + gmTest.rho);
    }
}
