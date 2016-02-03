/*
  AdvTransExample1.java illustrates transaction isolation
  and how to set the transaction isolation level to
  TRANSACTION_SERIALIZABLE for a Connection object,
  along with the subsequent effect on a transaction
*/

// import the JDBC packages

import java.sql.*;
import java.io.*;

public class AdvTransExample1 {

    public static void main(String[] args) throws SQLException, IOException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        // create a Connection object named rcConnection
        Connection rcConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1523:ORCL",
                "store_user",
                "store_password"
        );
        rcConnection.setAutoCommit(false);

        // create a Connection object named serConnection
        Connection serConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1523:ORCL",
                "store_user",
                "store_password"
        );
        serConnection.setAutoCommit(false);

        // set transaction isolation level to TRANSACTION_SERIALIZABLE
        // for serConnection - serConnection will no longer see
        // phantom rows, non-repeatable reads or dirty reads
        System.out.println("Setting transaction isolation level to " + "TRANSACTION_SERIALIZABLE for serConnection");
        serConnection.setTransactionIsolation(oracle.jdbc.OracleConnection.TRANSACTION_SERIALIZABLE);

        // create two Statement objects, named rcStatement and
        // serStatement
        Statement rcStatement = rcConnection.createStatement();
        Statement serStatement = serConnection.createStatement();

        // display all products using rcStatement
        System.out.println("List of products using rcStatement");
        displayProducts(rcStatement);

        // display all products using serStatement
        System.out.println("List of products using serStatement");
        displayProducts(serStatement);

        // add product #6 using rcStatement
        System.out.println("Adding product #13 using rcStatement");
        rcStatement.executeUpdate("INSERT INTO products " +
                "(id, type_id, name, description, price) VALUES " +
                "(SEQ_PRODUCTS.nextVal, 1, 'JDBC Programming', 'Java programming', 49.99)"
        );

        // update product #1 using rcStatement
        System.out.println("Updating product #1's name to New Science using rcStatement");
        rcStatement.executeUpdate("UPDATE products " +
                "SET name = 'New Science' " +
                "WHERE id = 1"
        );

        // commit the changes using rcConnection
        System.out.println("Committing changes using rcConnection");
        rcConnection.commit();

        // display all products using rcStatement
        System.out.println("List of products using rcStatement");
        displayProducts(rcStatement);

        // display all products using serStatement -
        // the new product (a phantom row) and the
        // modified product name (a non-repeatable read)
        // are not visible to serStatement because it has a transaction
        // isolation level of TRANSACTION_SERIALIZABLE
        System.out.println("List of products using serStatement");
        displayProducts(serStatement);

        // delete the new product, change the first product name
        // back to Modern Science, and commit the changes using
        // rcStatement
        rcStatement.executeUpdate("DELETE FROM products WHERE id = 19");

        rcStatement.executeUpdate("UPDATE products " +
                "SET name = 'Modern Science' " +
                "WHERE id = 1"
        );
        rcConnection.commit();

        // close the JDBC objects
        rcStatement.close();
        rcConnection.close();
        serStatement.close();
        serConnection.close();

    } // end of main()


    private static void displayProducts(Statement myStatement) throws SQLException {

        ResultSet productResultSet = myStatement.executeQuery("SELECT id, name FROM products");

        while (productResultSet.next()) {
            System.out.println(
                    productResultSet.getInt("id") + "  " +
                            productResultSet.getString("name") + " "
            );
        }

        productResultSet.close();

    } // end of displayProducts()

}