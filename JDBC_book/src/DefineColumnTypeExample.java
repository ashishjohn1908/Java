/*
  DefineColumnTypeExample.java shows how to define a column type
*/

// import the JDBC packages

import java.sql.*;

import oracle.jdbc.*;

public class DefineColumnTypeExample {

    public static void main(String[] args)
            throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(
                new oracle.jdbc.OracleDriver()
        );

        // create a Connection object and connect to the database
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

        // delete the rows from the perf_test table
        myStatement.execute("TRUNCATE TABLE perf_test");

        // insert a row into the perf_test table
        System.out.println("Adding row to perf_test table");
        myStatement.executeUpdate(
                "INSERT INTO perf_test " +
                        "(value) VALUES (1)"
        );
        myConnection.commit();

        // define the column type as Types.INTEGER for the first column
        // (the first column is the value column)
        System.out.println("Defining column type as Types.INTEGER for " +
                "first column");
        ((OracleStatement) myStatement).defineColumnType(
                1, Types.INTEGER
        );

        // create a ResultSet object
        ResultSet myResultSet = myStatement.executeQuery(
                "SELECT value " +
                        "FROM perf_test"
        );

        // read the row from the ResultSet
        while (myResultSet.next()) {
            System.out.println("value = " + myResultSet.getInt("value"));
        } // end of while loop

        // close the ResultSet, Statement and Connection objects
        myResultSet.close();
        myStatement.close();
        myConnection.close();

    } // end of main()

}