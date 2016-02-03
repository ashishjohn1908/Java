/*
  BatchingExample2.java shows how to use Oracle
  update batching to improve performance
*/

// import the JDBC packages

import java.sql.*;

import oracle.jdbc.*;

public class BatchingExample2 {

    // the TOTAL_NUM_ROWS constant is the total number of rows
    // that will be inserted; the BATCH_SIZE constant is the maximum
    // number of statements that may be batched at any one time
    public static final int TOTAL_NUM_ROWS = 2000;
    public static final int BATCH_SIZE = 10;

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

        // insert rows without batching
        System.out.println("Inserting " + TOTAL_NUM_ROWS +
                " rows without batching");
        insertRows(myConnection, false);

        // inserted rows with batching
        System.out.println("Inserting " + TOTAL_NUM_ROWS +
                " rows with Oracle update batching");
        insertRows(myConnection, true);

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    } // end of main()


    private static void insertRows(
            Connection myConnection, boolean batching
    ) throws SQLException {

        // create an OraclePreparedStatement object
        OraclePreparedStatement myOraclePrepStatement =
                (OraclePreparedStatement) myConnection.prepareStatement(
                        "INSERT INTO perf_test " +
                                "(value) VALUES (?)"
                );

        // if batching is true, then set the batch value to BATCH_SIZE
        if (batching) {
            System.out.println("Setting the batch value to " +
                    BATCH_SIZE);
            myOraclePrepStatement.setExecuteBatch(BATCH_SIZE);
        }

        // display the current batch value
        System.out.println("Batch value = " +
                myOraclePrepStatement.getExecuteBatch());

        // record the start time
        long start_time = System.currentTimeMillis();

        // insert the rows
        for (int count = 0; count < TOTAL_NUM_ROWS; count++) {
            myOraclePrepStatement.setInt(1, count);
            int rowsInserted = myOraclePrepStatement.executeUpdate();
        }

        // record the end time
        long end_time = System.currentTimeMillis();

        // display the total time taken to insert the rows
        System.out.println("Total time for inserting " + TOTAL_NUM_ROWS +
                " rows was " + (end_time - start_time) + " milliseconds");

        // rollback the SQL statements
        myConnection.rollback();

        // close the PreparedStatement object
        myOraclePrepStatement.close();

    } // end of insertRows()

}