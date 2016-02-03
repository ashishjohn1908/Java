/*
  PLSQLExample3.java shows how to handle a REF CURSOR
  using standard JDBC plus the oracle.jdbc.OracleTypes.CURSOR
  type definition
*/

// import the JDBC packages

import java.sql.*;

import oracle.jdbc.*;

public class PLSQLExample3 {

    public static void main(String args[]) throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        // create a Connection object, and connect to the database
        // as store_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection(  "jdbc:oracle:thin:@localhost:1521:ORCL",
                                                                "store_user",
                                                                "store_password"
                                                             );

        // create a CallableStatement object to call the
        // function get_customers_ref_cursor() in the package
        // ref_cursor_package
        CallableStatement myCallableStatement = myConnection.prepareCall( "{? = call ref_cursor_package.get_products_ref_cursor()}" );

        // register the output parameter as an Oracle CURSOR
        myCallableStatement.registerOutParameter(1, OracleTypes.CURSOR);

        // execute the call
        myCallableStatement.execute();

        // get the cursor using a call to the getObject() method,
        // the returned object must be cast to a ResultSet
        ResultSet productResultSet = (ResultSet) myCallableStatement.getObject(1);

        // display the column values
        while (productResultSet.next()) {
            System.out.println("id = " + productResultSet.getInt("id"));
            System.out.println("name = " + productResultSet.getString("name"));
            System.out.println("price = " +  productResultSet.getDouble("price"));
        } // end of while

        // close the JDBC objects
        productResultSet.close();
        myCallableStatement.close();
        myConnection.close();

    } // end of main()
}