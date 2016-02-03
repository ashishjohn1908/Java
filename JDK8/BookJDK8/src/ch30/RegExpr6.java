package ch30;

// Use the ? quantifier.

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegExpr6 {
    public static void main(String args[]) {
        // Use reluctant matching behavoir.
        Pattern pat = Pattern.compile("e.+?d");
        Matcher mat = pat.matcher("extend cup end table");

        while (mat.find())
            System.out.println("Match: " + mat.group());
    }
}
