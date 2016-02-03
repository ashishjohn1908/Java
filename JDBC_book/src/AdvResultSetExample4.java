/*
  AdvResultSetExample4.java shows the visibilty of changes
  to an updatable scrollable sensitive result set
*/

// import the JDBC packages

import java.sql.*;

public class AdvResultSetExample4 {

    public static void main(String args[])  throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        // create a Connection object, and connect to the database
        // as store_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection(  "jdbc:oracle:thin:@localhost:1523:ORCL",
                                                                "store_user",
                                                                "store_password"
                                                        );

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        // create a Statement object from which a scrollable sensitive
        // ResultSet object will be created
        Statement myStatement = myConnection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                             ResultSet.CONCUR_UPDATABLE
                                                             );

        // set the fetch size to 1
        myStatement.setFetchSize(1);

        // create a ResultSet object
        ResultSet customerResultSet = myStatement.executeQuery( "SELECT id, first_name, last_name, dob, phone " +
                                                                "FROM customers"
                                                              );
        System.out.println("Retrieved rows from customers table");

        // display row #2's id and last name
        customerResultSet.absolute(2);
        System.out.println("id = " +  customerResultSet.getInt("id"));
        System.out.println("last_name = " + customerResultSet.getString("last_name"));

        // update customer #2's last name to "Jones" using a separate
        // Statement object - this is an external update
        System.out.println("Updating customer #2's last name to 'Jones'");
        Statement updateStatement = myConnection.createStatement();
        updateStatement.execute("UPDATE customers " +
                                "SET last_name = 'Jones' " +
                                "WHERE id = 2"
        );
        myConnection.commit();

        // display all the customer ids and last names
        System.out.println("Rows in customerResultSet");
        customerResultSet.beforeFirst();
        while (customerResultSet.next()) {
            System.out.println("id = " + customerResultSet.getInt("id"));
            System.out.println("last_name = " + customerResultSet.getString("last_name"));
        } // end of while loop

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