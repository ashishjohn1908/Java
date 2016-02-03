/*
  AdvResultSetExample1.java shows how to use
  an insenstive scrollable result set
*/

// import the JDBC packages

import java.sql.*;

public class AdvResultSetExample1 {

    public static void main(String args[]) throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver( new oracle.jdbc.OracleDriver() );

        // create a Connection object, and connect to the database
        // as store_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:ORCL",
                                                                "store_user",
                                                                "store_password"
                                                             );

        // create a PreparedStatement object from which an insensitive
        // scrollable ResultSet object will be created
        PreparedStatement myPrepStatement = myConnection.prepareStatement("SELECT id, first_name, last_name, dob, phone " +
                                                                                    "FROM customers " +
                                                                                    "WHERE id <= ?" +
                                                                                    "ORDER BY id",
                                                                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                                                                            ResultSet.CONCUR_READ_ONLY
        );

        // bind the int value 5 to the PreparedStatement object
        myPrepStatement.setInt(1, 5);

        // create a ResultSet object
        ResultSet customerResultSet = myPrepStatement.executeQuery();

        // display the rows in the ResultSet in reverse order
        System.out.println("Customers in reverse order");
        customerResultSet.afterLast();
        while (customerResultSet.previous()) {
            System.out.println("id = " + customerResultSet.getInt("id"));
            System.out.println("first_name = " + customerResultSet.getString("first_name"));
            System.out.println("last_name = " + customerResultSet.getString("last_name"));
            System.out.println("dob = " + customerResultSet.getString("dob"));
            System.out.println("phone = " + customerResultSet.getString("phone"));
        } // end of while loop

        // navigate to row #3
        System.out.println("Going to row #3");
        customerResultSet.absolute(3);
        System.out.println("id = " + customerResultSet.getInt("id"));

        // navigate back two rows to row #1
        System.out.println("Going back two rows");
        customerResultSet.relative(-2);
        System.out.println("id = " + customerResultSet.getInt("id"));

        // navigate before first row
        customerResultSet.beforeFirst();
        if (customerResultSet.isBeforeFirst()) {
            System.out.println("Before first row");
            System.out.println("Current row = " + customerResultSet.getRow());
        }

        // navigate to the first row
        System.out.println("Going to first row");
        customerResultSet.first();
        System.out.println("Current row = " + customerResultSet.getRow());

        // close the ResultSet object using the close() method
        customerResultSet.close();

        // close the other JDBC objects
        myPrepStatement.close();
        myConnection.close();

    } // end of main()
}