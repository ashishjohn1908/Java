package ch19;

// Demonstrate a resource bundle.

import java.util.Locale;
import java.util.ResourceBundle;

class LRBDemo {
    public static void main(String args[]) {
        ResourceBundle rd = ResourceBundle.getBundle("ch19.SampleRB");

        System.out.println("English version: ");
        System.out.println("String for Title key : " +
                rd.getString("title"));

        System.out.println("String for StopText key: " +
                rd.getString("StopText"));

        System.out.println("String for StartText key: " +
                rd.getString("StartText"));

        rd = ResourceBundle.getBundle("ch19.SampleRB_de", Locale.GERMAN);

        System.out.println("\nGerman version: ");
        System.out.println("String for Title key : " +
                rd.getString("title"));

        System.out.println("String for StopText key: " +
                rd.getString("StopText"));

        System.out.println("String for StartText key: " +
                rd.getString("StartText"));
    }
}
