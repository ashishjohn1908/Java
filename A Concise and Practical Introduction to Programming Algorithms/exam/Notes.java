import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Notes {

    static String[] questions = {"1a", "1b", "2a", "2b", "2c", "2d", "2e", "2f",
            "2g", "2h", "2i", "2j", "2k", "3a", "3b", "3c", "3d", "3e", "3f", "4a",
            "4b", "4c", "4d"};

    // static final double q1a = 1, q1b = 2; // coef d'origine
    static final double q1a = 2, q1b = 4;

    //  static final double q2aj = 0.5, q2k = 1; // coef d'origine
    static final double q2aj = 1, q2k = 2;

    //  static final double q3ab = 0.5, q3cde = 1, q3f = 2; // coef d'origine
    static final double q3ab = 1, q3cde = 2, q3f = 4;

    // static final double q4a = 0.5, q4b = 1, q4c = 1.5, q4d = 2; // coef d'origine
    static final double q4a = 1, q4b = 2, q4c = 3, q4d = 4;

    static double[] bareme = {q1a, q1b, q2aj, q2aj, q2aj, q2aj, q2aj, q2aj,
            q2aj, q2aj, q2aj, q2aj, q2k, q3ab, q3ab, q3cde, q3cde, q3cde, q3f, q4a,
            q4b, q4c, q4d};

    static int count = 0;

    static double sum_pm = 0;

    static double[] sum_details = new double[bareme.length];

    static double sum_hc = 0, min_hc = 20, max_hc = 0;

    static double sum_module = 0, min_module = 20, max_module = 0;

    static String lettres = "ABCDEF";

    static int[] sum_lettres = new int[lettres.length()];

    static String ligne = null;

    static Formatter formatter = new Formatter(System.out, Locale.US);

    public static String nextLine(Scanner sc) {
        String ligne = null;
        do
            if (!sc.hasNextLine())
                return null;
            else
                ligne = sc.nextLine();
        while (ligne.length() == 0 || ligne.charAt(0) == '#');
        return ligne;
    }

    public static double parseQuestion(Scanner sl, int i, String login) {
        String q = sl.next();
        if (!q.equals(questions[i])) {
            System.err.println("manque ligne pour question : " + questions[i]
                    + " de " + login);
            System.exit(-1);
        }
        double n = 0;
        if (sl.hasNext()) {
            double c = sl.nextDouble();
            if (c == -1)
                c = 0;
            if (c < 0 || c > 1) {
                System.err.println("note c incorrecte : " + c + " pour question : "
                        + questions[i] + " de " + login);
                System.exit(-1);
            }
            double d = sl.nextDouble();
            if (d < 0 || d > 0.25) {
                System.err.println("note d incorrecte : " + d + " pour question : "
                        + questions[i] + " de " + login);
                System.exit(-1);
            }
            n = bareme[i] * c - d;
            if (n < 0)
                n = 0;
            if (c > 0 && n < bareme[i] / 4) {
                n = bareme[i] / 4;
                System.err.println("plancher pour question : " + questions[i] + " de "
                        + login);
            }
        }
        if (!sl.hasNext())
            System.err.println("ligne incomplete pour question : " + questions[i]
                    + " de " + login);
        return n;
    }

    public static double parseQuestions(Scanner sc, String login) {
        double hc = 0;
        for (int i = 0; i < bareme.length; ++i) {
            ligne = nextLine(sc);
            if (ligne == null) {
                System.err.println("manque ligne pour question : " + questions[i]
                        + " de " + login);
                System.exit(-1);
            }
            Scanner sl = new Scanner(ligne);
            sl.useLocale(Locale.US);
            double n = parseQuestion(sl, i, login);
            hc += n;
            sum_details[i] += n;
        }
        return hc;
    }

    public static void stats(double pm, double hc2, double module, int p) {
        sum_pm += pm;
        sum_hc += hc2;
        if (min_hc > hc2)
            min_hc = hc2;
        if (max_hc < hc2)
            max_hc = hc2;
        sum_module += module;
        if (min_module > module)
            min_module = module;
        if (max_module < module)
            max_module = module;
        sum_lettres[p]++;
        ++count;
    }

    public static boolean parseBloc(Scanner sc, boolean verbose) {
        String ligne = nextLine(sc);
        if (ligne == null)
            return false;
        String[] champs = ligne.split(";");
        if (champs.length != 12) {
            System.err.println("erreur " + champs.length
                    + " champ(s) sur la ligne d'entete :\n\"" + ligne + '"');
            System.exit(-1);
        }
        String login = champs[4];
        if (verbose) {
            System.out.println("================================");
            System.out.println(login);

        }
        int pm = Integer.parseInt(champs[8]);
        double hc = parseQuestions(sc, login);
        // arrondi au 1/2 point sup.
        double hc2 = (int) (2 * hc + 0.5) / 2.0;
        double module = (pm + 2 * hc2) / 3;
        int p = 0;
        double mod = module;
        while (mod < 16) {
            p++;
            mod += 4;
        }
        char lettre = lettres.charAt(p);
        if (verbose) {
            System.out.println("note PM : " + pm);
            formatter.format("note HC : %.2f  arrondi %.1f\n", hc, hc2);
            formatter.format("note module : %.1f\n", module);
            System.out.println("lettre module : " + lettre);
        }
        for (int i = 0; i < champs.length - 1; ++i)
            System.out.print(champs[i] + ';');
        formatter.format("%.1f;%.1f;%1c\n", hc2, module, lettre);
        stats(pm, hc2, module, p);
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        boolean verbose = true;
        while (sc.hasNextLine())
            parseBloc(sc, verbose);

        System.out.println("================================");
        System.out.println(count + " copies");
        formatter.format("moyenne PM : %.2f\n", (sum_pm / count));
        double sum_bareme = 0;
        for (int i = 0; i < bareme.length; ++i) {
            formatter.format("moyenne %s : %.2f / %.1f\n", questions[i],
                    (sum_details[i] / count), bareme[i]);
            sum_bareme += bareme[i];
        }
        formatter.format("moyenne HC : %.2f / %.1f  [ %.1f .. %.1f ]\n",
                (sum_hc / count), sum_bareme, min_hc, max_hc);
        formatter.format("moyenne module : %.2f  [ %.1f .. %.1f ]\n",
                (sum_module / count), min_module, max_module);
        System.out.println("================================");
        for (int i = 0; i < lettres.length(); ++i)
            System.out.println(lettres.charAt(i) + " : " + sum_lettres[i]);
    }
}
