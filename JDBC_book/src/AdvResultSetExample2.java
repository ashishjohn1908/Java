/*
  AdvResultSetExample2.java shows how to use
  an updatable result set
*/

// import the JDBC packages

import java.sql.*;

public class AdvResultSetExample2 {

    public static void main(String args[]) throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        // create a Connection object, and connect to the database
        // as store_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1523:ORCL",
                "store_user",
                "store_password"
        );

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        // create a Statement object from which an updatable
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

        // update row #2's first name and dob
        System.out.println("Updating the first_name and dob for row #2");
        customerResultSet.absolute(2);
        String newFirstName = "Greg";
        customerResultSet.updateString("first_name", newFirstName);
        java.sql.Date dob = Date.valueOf("1969-01-01");
        customerResultSet.updateDate("dob", dob);
        customerResultSet.updateRow();

        // display the new first name and dob
        System.out.println("first_name = " +
                customerResultSet.getString("first_name"));
        System.out.println("dob = " +
                customerResultSet.getString("dob"));

        // insert a new row
        System.out.println("Inserting new row");
        customerResultSet.moveToInsertRow();
        customerResultSet.updateInt("id", 6);
        customerResultSet.updateString("first_name", "Jason");
        customerResultSet.updateString("last_name", "Price");
        customerResultSet.updateDate("dob", dob);
        customerResultSet.insertRow();
        customerResultSet.moveToCurrentRow();

        // delete row #5
        System.out.println("Deleting row #5");
        customerResultSet.absolute(5);
        customerResultSet.deleteRow();

        // display the rows in the ResultSet
        System.out.println("Rows in customerResultSet");
        customerResultSet.beforeFirst();
        while (customerResultSet.next()) {
            System.out.println("id = " +
                    customerResultSet.getInt("id"));
            System.out.println("first_name = " +
                    customerResultSet.getString("first_name"));
            System.out.println("last_name = " +
                    customerResultSet.getString("last_name"));
            System.out.println("dob = " +
                    customerResultSet.getString("dob"));
            System.out.println("phone = " +
                    customerResultSet.getString("phone"));
        } // end of while loop

        // rollback the changes made to the database
        myConnection.rollback();

        // close this ResultSet object using the close() method
        customerResultSet.close();

        // close the other JDBC objects
        myStatement.close();
        myConnection.close();

    } // end of main()
}