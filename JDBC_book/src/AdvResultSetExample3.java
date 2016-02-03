/*
  AdvResultSetExample3.java shows the use of the refreshRow()
  method to refresh an updated row in an updatable scrollable
  insensitive result set
*/

// import the JDBC packages

import java.sql.*;

public class AdvResultSetExample3 {

    public static void main(String args[])
            throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(
                new oracle.jdbc.OracleDriver()
        );

        // create a Connection object, and connect to the database
        // as store_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1523:ORCL",
                "store_user",
                "store_password"
        );

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        // create a Statement object from which a scrollable insensitive
        // ResultSet object will be created
        Statement myStatement = myConnection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );

        // create a ResultSet object
        ResultSet customerResultSet = myStatement.executeQuery(
                "SELECT id, first_name, last_name, dob, phone " +
                        "FROM customers"
        );
        System.out.println("Retrieved rows from customers table");

        // display customer #2's id and last name
        customerResultSet.absolute(2);
        System.out.println("id = " +
                customerResultSet.getInt("id"));
        System.out.println("last_name = " +
                customerResultSet.getString("last_name"));

        // update customer #2's last name to "Jones" using a separate
        // Statement object - this is an external update
        System.out.println("Updating customer #2's last name to 'Jones'");
        Statement updateStatement = myConnection.createStatement();
        updateStatement.execute(
                "UPDATE customers " +
                        "SET last_name = 'Jones' " +
                        "WHERE id = 2"
        );
        myConnection.commit();

        // refresh the current row, in this case row #2
        System.out.println("Refeshing row #2");
        customerResultSet.refreshRow();

        // re-display row #2's id and last name
        System.out.println("id = " +
                customerResultSet.getInt("id"));
        System.out.println("last_name = " +
                customerResultSet.getString("last_name"));

        // set customer #2's last name back to the original
        updateStatement.execute(
                "UPDATE customers " +
                        "SET last_name = 'Green' " +
                        "WHERE id = 2"
        );
        myConnection.commit();

        // close this ResultSet object using the close() method
        customerResultSet.close();

        // close the other JDBC objects
        myStatement.close();
        updateStatement.close();
        myConnection.close();

    } // end of main()
}