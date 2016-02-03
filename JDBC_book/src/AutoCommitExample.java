/*
  AutoCommitExample.java shows how disabling auto-commit
  improves performance
*/

// import the JDBC packages

import java.sql.*;

public class AutoCommitExample {

    // the TOTAL_NUM_ROWS constant is the total number of rows
    // that will be inserted into the perf_test table
    public static final int TOTAL_NUM_ROWS = 2000;

    public static void main(String[] args) throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        // create a Connection object and connect to the database
        // as store_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1523:ORCL", "store_user", "store_password");

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // truncate the perf_test table
        myStatement.execute("TRUNCATE TABLE perf_test");

        // enable auto-commit mode
        System.out.println("Enabling auto-commit mode");
        myConnection.setAutoCommit(true);

        // insert rows into the perf_test table
        insertRows(myConnection);

        // truncate the perf_test table again
        myStatement.execute("TRUNCATE TABLE perf_test");

        // disable auto-commit mode
        System.out.println("Disabling auto-commit mode");
        myConnection.setAutoCommit(false);

        // insert rows again
        insertRows(myConnection);

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    } // end of main()


    private static void insertRows(Connection myConnection) throws SQLException {

        // create a PreparedStatement object
        PreparedStatement myPrepStatement = myConnection.prepareStatement("INSERT INTO perf_test " +
                "(value) VALUES (?)"
        );

        // record the start time
        long start_time = System.currentTimeMillis();

        // insert the rows
        for (int count = 0; count < TOTAL_NUM_ROWS; count++) {
            myPrepStatement.setInt(1, count);
            myPrepStatement.executeUpdate();
        }

        // record the end time
        long end_time = System.currentTimeMillis();

        // display the total time taken to insert the rows
        System.out.println("Total time for inserting " + TOTAL_NUM_ROWS + " rows was " + (end_time - start_time) + " milliseconds");

        // commit the SQL statements
        myConnection.commit();

        // close the PreparedStatement object
        myPrepStatement.close();

    } // end of insertRows()

}