/*
  MetaDataExample.java shows how to obtain and
  display result set and database meta data
*/

// import the JDBC packages

import java.sql.*;

public class MetaDataExample {

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

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // create a ResultSet object
        ResultSet customerResultSet = myStatement.executeQuery(
                "SELECT id, first_name, last_name, dob, phone " +
                        "FROM customers"
        );
        System.out.println("Retrieved rows from customers table");

        // get and display result set meta data
        ResultSetMetaData myRSMetaData = customerResultSet.getMetaData();
        System.out.println("Result set meta data follows");
        int columnCount = myRSMetaData.getColumnCount();
        System.out.println("Column count = " + columnCount);
        for (int counter = 1; counter <= columnCount; counter++) {
            System.out.println("Column name = " +
                    myRSMetaData.getColumnName(counter));
            System.out.println("Column type = " +
                    myRSMetaData.getColumnType(counter));
            System.out.println("Column type name = " +
                    myRSMetaData.getColumnTypeName(counter));
            System.out.println("Column display size = " +
                    myRSMetaData.getColumnDisplaySize(counter));
            System.out.println("Column is nullable = " +
                    myRSMetaData.isNullable(counter));
            System.out.println("Column precision = " +
                    myRSMetaData.getPrecision(counter));
            System.out.println("Column scale = " +
                    myRSMetaData.getScale(counter));
        }

        // get and display database meta data
        DatabaseMetaData myDBMetaData = myConnection.getMetaData();
        System.out.println("Database meta data follows");
        System.out.println("Database product name = " +
                myDBMetaData.getDatabaseProductName());
        System.out.println("Database URL = " +
                myDBMetaData.getURL());
        System.out.println("Database user name = " +
                myDBMetaData.getUserName());

        // close the ResultSet object using the close() method
        customerResultSet.close();

        // close the other JDBC objects
        myStatement.close();
        myConnection.close();

    } // end of main()
}