package ch30;

// Use a quantifier.

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegExpr4 {
    public static void main(String args[]) {
        Pattern pat = Pattern.compile("W+");
        Matcher mat = pat.matcher("W WW WWW");

        while (mat.find())
            System.out.println("Match: " + mat.group());
    }
}
