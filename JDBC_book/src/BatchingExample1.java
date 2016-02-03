/*
  BatchingExample1.java shows how to use standard update
  batching to improve performance
*/

// import the JDBC packages

import java.sql.*;

public class BatchingExample1 {

    // the TOTAL_NUM_ROWS constant is the total number of rows
    // that will be inserted; the BATCH_SIZE constant is the maximum
    // number of statements that may be batched at any one time
    public static final int TOTAL_NUM_ROWS = 2000;
    public static final int BATCH_SIZE = 10;

    public static void main(String[] args) throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        // create a Connection object and connect to the database
        // as store_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1523:ORCL",
                "store_user",
                "store_password"
        );

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // delete the rows from the perf_test table
        myStatement.execute("TRUNCATE TABLE perf_test");

        // insert rows without batching (as indicated by the
        // second parameter that is set to false)
        System.out.println("Inserting " + TOTAL_NUM_ROWS + " rows without batching");
        insertRows(myConnection, false);

        // insert rows with batching
        System.out.println("Inserting " + TOTAL_NUM_ROWS + " rows with standard update batching");
        insertRows(myConnection, true);

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    } // end of main()


    private static void insertRows(Connection myConnection, boolean batching) throws SQLException {

        // create a PreparedStatement object
        PreparedStatement myPrepStatement = myConnection.prepareStatement("INSERT INTO perf_test " +
                "(value) VALUES (?)"
        );
        // record the start time
        long start_time = System.currentTimeMillis();

        // insert the rows
        int count2 = 0;
        for (int count = 0; count < TOTAL_NUM_ROWS; count++) {
            myPrepStatement.setInt(1, count);

            // if batching is true, then batch the SQL statements,
            // otherwise simply execute the statement
            if (batching) {

                myPrepStatement.addBatch();
                count2++;

                // execute the batch when there are a total of
                // BATCH_SIZE SQL statements in the batch
                if (count2 % BATCH_SIZE == 0) {
                    int[] rowsInserted = myPrepStatement.executeBatch();
                }

            } else {
                myPrepStatement.execute();
            }
        }

        // record the end time
        long end_time = System.currentTimeMillis();

        // display the total time taken to insert the rows
        System.out.println("Total time for inserting " + TOTAL_NUM_ROWS + " rows was " + (end_time - start_time) + " milliseconds");

        // rollback the SQL statements
        //myConnection.rollback();
          myConnection.commit();

        // close the PreparedStatement object
        myPrepStatement.close();

    } // end of insertRows()

}