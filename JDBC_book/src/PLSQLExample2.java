/*
  PLSQLExample2.java shows how to handle a REF CURSOR
  using the Oracle JDBC extensions
*/

// import the JDBC packages

import java.sql.*;

// import the Oracle JDBC extension packages
import oracle.sql.*;
import oracle.jdbc.*;

public class PLSQLExample2 {

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

        // create an OracleCallableStatement object to call the
        // function get_products_ref_cursor() in the package
        // ref_cursor_package
        OracleCallableStatement myCallableStatement =
                (OracleCallableStatement) myConnection.prepareCall(
                        "{? = call ref_cursor_package.get_products_ref_cursor()}"
                );

        // register the output parameter as an Oracle CURSOR
        myCallableStatement.registerOutParameter(1, OracleTypes.CURSOR);

        // execute the call
        myCallableStatement.execute();

        // get the cursor using the getCursor() method
        OracleResultSet productResultSet =
                (OracleResultSet) myCallableStatement.getCursor(1);

        // display the column values
        while (productResultSet.next()) {
            System.out.println("id = " +
                    productResultSet.getInt("id"));
            System.out.println("name = " +
                    productResultSet.getString("name"));
            System.out.println("price = " +
                    productResultSet.getDouble("price"));
        } // end of while

        // close the JDBC objects
        productResultSet.close();
        myCallableStatement.close();
        myConnection.close();

    } // end of main()
}