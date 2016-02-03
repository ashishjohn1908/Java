package ch30;

// Use find() to find a subsequence.

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegExpr2 {
    public static void main(String args[]) {
        Pattern pat = Pattern.compile("Java");
        Matcher mat = pat.matcher("Java 8");

        System.out.println("Looking for Java in Java 8.");

        if (mat.find()) System.out.println("subsequence found");
        else System.out.println("No Match");
    }
}

