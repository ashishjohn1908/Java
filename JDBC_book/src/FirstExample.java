/*
  FirstExample.java connects to the database as the "scott"
  user; the program then retrieves this username along with
  the current date and time from the database
*/

// import the standard JDBC package

import java.sql.*;

public class FirstExample {

    public static void main(String args[]) {

        // declare Connection and Statement objects
        Connection myConnection = null;
        Statement myStatement = null;

        try {

            // register the Oracle JDBC drivers
            DriverManager.registerDriver(
                    new oracle.jdbc.OracleDriver()
            );

            // create a Connection object, and connect to the database
            // as scott using the Oracle JDBC Thin driver
            myConnection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1523:ORCL",
                    "scott",
                    "tiger"
            );

            // create a Statement object
            myStatement = myConnection.createStatement();

            // create a ResultSet object, and populate it with the
            // result of a SELECT statement that retrieves the
            // user and sysdate variables from the database via
            // the dual table - the executeQuery() method of the
            // Statement object is used to perform the SELECT
            ResultSet myResultSet = myStatement.executeQuery(
                    "SELECT user, sysdate " +
                            "FROM dual"
            );

            // retrieve the row from the ResultSet using the
            // next() method
            myResultSet.next();

            // retrieve the user from the row in the ResultSet using the
            // getString() method
            String user = myResultSet.getString("user");

            // retrieve the sysdate from the row in the ResultSet using
            // the getTimestamp() method
            Timestamp currentDateTime =
                    myResultSet.getTimestamp("sysdate");

            System.out.println("Hello " + user +
                    ", the current date and time is " + currentDateTime);

            // close this ResultSet object using the close() method
            myResultSet.close();

        } catch (SQLException e) {

            System.out.println("Error code = " + e.getErrorCode());
            System.out.println("Error message = " + e.getMessage());

        } finally {

            try {

                // close the Statement object using the close() method
                if (myStatement != null) {
                    myStatement.close();
                }

                // close the Connection object using the close() method
                if (myConnection != null) {
                    myConnection.close();
                }

            } catch (SQLException e) {

                System.out.println("Error code = " + e.getErrorCode());
                System.out.println("Error message = " + e.getMessage());

            }

        }

    } // end of main()

}