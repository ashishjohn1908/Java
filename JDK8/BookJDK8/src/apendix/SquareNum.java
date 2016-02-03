package apendix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class demonstrates documentation comments.
 *
 * @author Herbert Schildt
 * @version 1.2
 */
public class SquareNum {
    /**
     * This method demonstrates square().
     *
     * @param args Unused.
     * @throws IOException On input error.
     * @see IOException
     */

    public static void main(String args[])
            throws IOException {
        SquareNum ob = new SquareNum();
        double val;

        System.out.println("Enter value to be squared: ");
        val = ob.getNumber();
        val = ob.square(val);

        System.out.println("Squared value is " + val);
    }

    /**
     * This method returns the square of num.
     * This is a multiline description.  You can use
     * as many lines as you like.
     *
     * @param num The value to be squared.
     * @return num squared.
     */
    public double square(double num) {
        return num * num;
    }

    /**
     * This method inputs a number from the user.
     *
     * @return The value input as a double.
     * @throws IOException On input error.
     * @see IOException
     */
    public double getNumber() throws IOException {
        // create a BufferedReader using System.in
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader inData = new BufferedReader(isr);
        String str;

        str = inData.readLine();
        return (new Double(str)).doubleValue();
    }
}

