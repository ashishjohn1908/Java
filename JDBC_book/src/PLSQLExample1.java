/*
  PLSQLExample1.java shows how to call a PL/SQL procedure
  and function
*/

// import the JDBC packages

import java.sql.*;

public class PLSQLExample1 {

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

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // display product #1's id and price
        System.out.println("Id and original price");
        displayProduct(myStatement, 1);

        // create a CallableStatement object to call the
        // PL/SQL procedure update_product_price()
        CallableStatement myCallableStatement = myConnection.prepareCall(
                "{call update_product_price(?, ?)}"
        );

        // bind values to the CallableStatement object's parameters
        myCallableStatement.setInt(1, 1);
        myCallableStatement.setDouble(2, 1.1);

        // execute the CallableStatement object - this increases the price
        // for product #1 by 10%
        myCallableStatement.execute();
        System.out.println("Increased price by 10%");
        displayProduct(myStatement, 1);

        // call the PL/SQL function update_product_price_func()
        myCallableStatement = myConnection.prepareCall(
                "{? = call update_product_price_func(?, ?)}"
        );

        // register the output parameter, and bind values to
        // the CallableStatement object's parameters
        myCallableStatement.registerOutParameter(1, java.sql.Types.INTEGER);
        myCallableStatement.setInt(2, 1);
        myCallableStatement.setDouble(3, 0.8);

        // execute the CallableStatement object - this decreases the new
        // price for product #1 by 20%
        myCallableStatement.execute();
        int result = myCallableStatement.getInt(1);
        System.out.println("Result returned from function = " + result);
        System.out.println("Decreased new price by 20%");
        displayProduct(myStatement, 1);

        // reset the price back to the original value
        myStatement.execute(
                "UPDATE products " +
                        "SET price = 19.95" +
                        "WHERE id = 1"
        );
        myConnection.commit();
        System.out.println("Reset price back to 19.95");

        // close the JDBC objects
        myCallableStatement.close();
        myStatement.close();
        myConnection.close();

    } // end of main()


    public static void displayProduct(
            Statement myStatement,
            int id
    ) throws SQLException {

        // display the id and price columns
        ResultSet productResultSet = myStatement.executeQuery(
                "SELECT id, price " +
                        "FROM products " +
                        "WHERE id = " + id
        );
        productResultSet.next();
        System.out.println("id = " + productResultSet.getInt("id"));
        System.out.println("price = " + productResultSet.getDouble("price"));

        productResultSet.close();

    } // end of displayProduct()

}