/*
  JvmExample1.java contains methods that are designed
  to run in the Oracle JVM.
*/

// import the JDBC packages

import java.sql.*;

public class JvmExample1 {

    public static void displayMessageInTraceFile()
            throws SQLException {

        // the following line writes output to the database trace file
        System.out.println("Output from displayMessageInTraceFile() " +
                "appears in the current database trace file");

    }


    public static void displayMessageOnScreen()
            throws SQLException {

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

        // the following output is displayed on the screen
        System.out.println("Output from displayMessageOnScreen() " +
                "appears on the screen");

        // call dbms_output.enable(2000) to
        // set up an output buffer of 2000 bytes
        myStatement.execute(
                "{call dbms_output.enable(2000)}"
        );

        // call dbms_output.put_line()
        // to write the output to this buffer
        myStatement.execute(
                "{call dbms_output.put_line(" +
                        "'Displayed on the screen from dbms_output.put_line()'" +
                        ")}"
        );

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    }


    public static void displayProduct(
            int id
    ) throws SQLException {

        // create a Connection object
        Connection myConnection = DriverManager.getConnection(
                "jdbc:default:connection"
        );

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // set up an output buffer
        myStatement.execute(
                "{call dbms_java.set_output(2000)}"
        );

        // retrieve the column values for the specified product
        ResultSet productResultSet = myStatement.executeQuery(
                "SELECT type_id, name, description, price " +
                        "FROM products " +
                        "WHERE id = " + id
        );

        // display the column values
        productResultSet.next();
        System.out.println("type_id = " + productResultSet.getString("type_id"));
        System.out.println("name = " + productResultSet.getString("name"));
        System.out.println("description = " + productResultSet.getString("description"));
        System.out.println("price = " + productResultSet.getString("price"));
        productResultSet.close();

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    }


    public static void addProduct(
            int type_id,
            String name,
            String description,
            double price
    )
            throws SQLException {

        // create a Connection object
        Connection myConnection = DriverManager.getConnection(
                "jdbc:default:connection"
        );

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // set up an output buffer
        myStatement.execute(
                "{call dbms_java.set_output(2000)}"
        );

        // retrieve the maximum product id using the SQL MAX() function
        ResultSet productResultSet = myStatement.executeQuery(
                "SELECT MAX(id) " +
                        "FROM products"
        );
        productResultSet.next();
        int maxId = productResultSet.getInt(1);
        productResultSet.close();

        // add 1 to the maximum id to generate the id for the new row
        int id = maxId + 1;

        // perform SQL INSERT statement to add a new row to the
        // products table
        myStatement.executeUpdate(
                "INSERT INTO products " +
                        "(id, type_id, name, description, price) VALUES (" +
                        id + ", " + type_id + ", '" + name + "', '" + description + "', " +
                        price + ")"
        );
        System.out.println("Added new product with an id of " + id);

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    }


    public static int countProducts()
            throws SQLException {

        int numberOfProducts = 0;

        // create a Connection object
        Connection myConnection = DriverManager.getConnection(
                "jdbc:default:connection"
        );

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // set up an output buffer
        myStatement.execute(
                "{call dbms_java.set_output(2000)}"
        );

        // retrieve the number of products using the SQL COUNT() function
        ResultSet productResultSet = myStatement.executeQuery(
                "SELECT COUNT(*) " +
                        "FROM products"
        );
        productResultSet.next();
        numberOfProducts = productResultSet.getInt(1);
        productResultSet.close();

        System.out.println("There are " + numberOfProducts + " products");

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

        return numberOfProducts;

    }

}