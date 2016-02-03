/*
   JvmExample3.java illustrates how to
   replace the PL/SQL code of the trigger
   before_product_price_update with Java.
*/

// import the JDBC packages

import java.sql.*;

public class JvmExample3 {

    public static void triggerCode(

            int product_id,
            double old_price,
            double new_price

    ) throws SQLException {

        // create a Connection object, and use the default
        // database connection provided by the JDBC server-side
        // internal driver
        Connection myConnection = DriverManager.getConnection(
                "jdbc:default:connection"
        );

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // call dbms_java.set_output(2000) to
        // set up an output buffer of 2000 bytes, this causes
        // output from System.out.println() calls to be routed
        // to this buffer
        myStatement.execute(
                "{call dbms_java.set_output(2000)}"
        );

        System.out.println("For product id " + product_id);
        System.out.println("Old price = " + old_price);
        System.out.println("New price = " + new_price);
        System.out.println("The price reduction is more than 25%");

        // insert row into the product_price_audit table
        myStatement.executeUpdate(
                "INSERT INTO product_price_audit " +
                        "(product_id, old_price, new_price) VALUES (" +
                        product_id + ", " + old_price + ", " + new_price +
                        ")"
        );

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    } // end of triggerCode()

}