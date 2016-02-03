/*
  PrefetchingExample1.java shows how to use standard JDBC row
  prefetching to improve performance
*/

// import the JDBC packages

import java.sql.*;

public class PrefetchingExample1 {

    // the TOTAL_NUM_ROWS constant is the total number of rows
    // that will be inserted
    public static final int TOTAL_NUM_ROWS = 2000;

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

        // insert a total of TOTAL_NUM_ROWS to the perf_test table
        System.out.println("Adding " + TOTAL_NUM_ROWS +
                " rows to perf_test table");
        for (int count = 0; count < TOTAL_NUM_ROWS; count++) {
            myStatement.executeUpdate(
                    "INSERT INTO perf_test " +
                            "(value) VALUES (" + count + ")"
            );
        }
        myConnection.commit();

        // retrieve the rows with a fetch size of 1
        retrieveRows(myStatement, 1);

        // retrieve the rows again, this time with a fetch size of 10
        retrieveRows(myStatement, 10);

        // retrieve the rows again, this time with a fetch size of 20
        retrieveRows(myStatement, 20);

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    } // end of main()


    private static void retrieveRows(
            Statement myStatement, int fetchSize
    ) throws SQLException {

        // set the fetch size
        System.out.println("Setting the statement fetch size to " +
                fetchSize);
        myStatement.setFetchSize(fetchSize);

        // display the statement fetch size
        System.out.println("Statement fetch size = " +
                myStatement.getFetchSize());

        // create a ResultSet object
        ResultSet myResultSet = myStatement.executeQuery(
                "SELECT value " +
                        "FROM perf_test"
        );

        // display the result set fetch size
        System.out.println("Result set fetch size = " +
                myResultSet.getFetchSize());

        // record the start time
        long start_time = System.currentTimeMillis();

        // retrieve the rows from the ResultSet object
        while (myResultSet.next()) {
            // do nothing
        } // end of while loop

        // record the end time
        long end_time = System.currentTimeMillis();

        // display the total time taken to retrieve the rows
        System.out.println("Total time for retrieving " +
                TOTAL_NUM_ROWS + " rows was " + (end_time - start_time) +
                " milliseconds");

        // close the ResultSet object
        myResultSet.close();

    } // end of retrieveRows()

}