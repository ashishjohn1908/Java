package BaseStats;

import static java.lang.Math.signum;
import static java.lang.Math.sqrt;

/**
 * Created by IntelliJ IDEA.
 * User: plamen
 * Date: 24-Apr-2009
 * Time: 20:12:08
 * To change this template use File | Settings | File Templates.
 */

public enum Bivnorm {
    bivar_params {
        public double evalArgs(double a, double b, double p) {//System.out.println("a=="+a+"   b=="+b+"    p=="+p);
            double paramval = (a * b * p);

            int caseval = 0;
            int casea = 0;
            int caseb = 0;
            double val1 = 0.0;
            double val2 = 0.0;
            double densityf = 0.0;
            if (paramval <= 0) {
                caseval = (a <= 0 & b <= 0 & p <= 0) ? 1 : (a <= 0 & b >= 0 & p >= 0) ? 2 : (a >= 0 & b <= 0 & p >= 0) ? 3 : (a >= 0 & b >= 0 & p <= 0) ? 4 : caseval;
            } else {
                int signa;
                int signb;
                signa = (signum(a) == (0 | 1)) ? 1 : -1;
                signb = (signum(b) == (0 | 1)) ? 1 : -1;
                // System.out.println("sign a=="+signa+"sign b=="+signb);
                double p1 = ((((p * a) - b) * signa) / (sqrt((a * a) - 2 * p * a * b + (b * b))));
                double p2 = ((((p * b) - a) * signb) / (sqrt((a * a) - 2 * p * a * b + (b * b))));
                double delta = ((1 - signa * signb) / 4.0);
                double ba = 0.0;
                casea = (a <= 0 & ba <= 0 & p1 <= 0) ? 1 : (a <= 0 & ba >= 0 & p1 >= 0) ? 2 : (a >= 0 & ba <= 0 & p1 >= 0) ? 3 : (a >= 0 & ba >= 0 & p1 <= 0) ? 4 : caseval;
                double aa = b;
                caseb = (aa <= 0 & ba <= 0 & p2 <= 0) ? 1 : (aa <= 0 & ba >= 0 & p2 >= 0) ? 2 : (aa >= 0 & ba <= 0 & p2 >= 0) ? 3 : (aa >= 0 & ba >= 0 & p2 <= 0) ? 4 : caseval;
                val1 = casea == 1 ? (allvars_less.valuesz(a, 0.0, p1)) : casea == 2 ? (limita_less.valuesz(a, 0.0, p1)) : casea == 3 ? (limitb_less.valuesz(a, 0.0, p1)) : (varrho_less.valuesz(a, 0.0, p1));
                val2 = caseb == 1 ? (allvars_less.valuesz(b, 0.0, p2)) : caseb == 2 ? (limita_less.valuesz(b, 0.0, p2)) : caseb == 3 ? (limitb_less.valuesz(b, 0.0, p2)) : (varrho_less.valuesz(b, 0.0, p2));
                densityf = ((val1 + val2) - delta);
                return densityf;
            }
            switch (caseval) {
                case 1:
                    densityf = allvars_less.valuesz(a, b, p);
                    break;
                case 2:
                    densityf = limita_less.valuesz(a, b, p);
                    break;
                case 3:
                    densityf = limitb_less.valuesz(a, b, p);
                    break;
                case 4:
                    densityf = varrho_less.valuesz(a, b, p);
                    break;

            }


            return densityf;
        }
    },
    limita_less {
        double valuesz(double a, double b, double p) {
            Probnorm pn = new Probnorm();

            return ((pn.ncDisfnc(a)) - (pn.cumBiv(a, -b, -p)));
        }


    },

    allvars_less {
        double valuesz(double a, double b, double p) {
            Probnorm pn = new Probnorm();

            return pn.cumBiv(a, b, p);

        }
    },
    limitb_less {
        double valuesz(double a, double b, double p) {
            Probnorm pn = new Probnorm();

            return (pn.ncDisfnc(b) - pn.cumBiv(-a, b, -p));

        }

    },

    varrho_less {
        double valuesz(double a, double b, double p) {
            Probnorm pn = new Probnorm();

            return (((pn.ncDisfnc(a) + pn.ncDisfnc(b)) - 1) + pn.cumBiv(-a, -b, p));
        }

    };

    /**
     * Creates a new instance of Bivnorm
     */
    Bivnorm() {
    }

    double xvalue;

    double valuesz(double a, double b, double p) {


        throw new UnsupportedOperationException("Not yet implemented");
    }

    public double evalArgs(double a, double b, double p) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
