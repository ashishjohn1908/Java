package ch30;

// Use a character class.

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegExpr7 {
    public static void main(String args[]) {
        // Match lowercase words.
        Pattern pat = Pattern.compile("[a-z]+");
        Matcher mat = pat.matcher("this is a test.");

        while (mat.find())
            System.out.println("Match: " + mat.group());
    }
}
