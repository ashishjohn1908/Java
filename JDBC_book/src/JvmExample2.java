/*
  JvmExample2.java shows how to call
  Java stored programs.
*/

// import the JDBC packages

import java.sql.*;

public class JvmExample2 {

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

        // create a CallableStatement object to call the
        // Java stored procedure jvm_example1.add_product()
        CallableStatement myCallableStatement = myConnection.prepareCall(
                "{call jvm_example1.add_product(?, ?, ?, ?)}"
        );

        // bind values to the CallableStatement object's parameters
        myCallableStatement.setInt(1, 1);
        myCallableStatement.setString(2, "Enterprise JavaBeans");
        myCallableStatement.setString(3, "A book on programming EJBs");
        myCallableStatement.setDouble(4, 35.95);

        // execute the CallableStatement object
        myCallableStatement.execute();
        System.out.println("Added a new product");

        // display the product
        displayProduct(myStatement, 16);

        // call the Java stored function jvm_example1.count_products()
        myCallableStatement = myConnection.prepareCall(
                "{? = call jvm_example1.count_products()}"
        );

        // register the output parameter, and bind values to
        // the CallableStatement object's parameters
        myCallableStatement.registerOutParameter(1, java.sql.Types.INTEGER);

        // execute the CallableStatement object
        myCallableStatement.execute();
        int result = myCallableStatement.getInt(1);
        System.out.println("Total number of products = " + result);

        // close the JDBC objects
        myStatement.close();
        myCallableStatement.close();
        myConnection.close();

    } // end of main()

    public static void displayProduct(
            Statement myStatement,
            int id
    ) throws SQLException {

        // display the id and price columns
        ResultSet productResultSet = myStatement.executeQuery(
                "SELECT id, type_id, name, description, price " +
                        "FROM products " +
                        "WHERE id = " + id
        );
        productResultSet.next();
        System.out.println("id = " + productResultSet.getString("id"));
        System.out.println("type_id = " + productResultSet.getString("type_id"));
        System.out.println("name = " + productResultSet.getString("name"));
        System.out.println("description = " + productResultSet.getString("description"));
        System.out.println("price = " + productResultSet.getString("price"));
        productResultSet.close();

    } // end of displayProduct()

}