/*
  StatementCacheExample2.java shows the use of
  explicit statement caching
*/

// import the JDBC packages

import java.sql.*;

import oracle.jdbc.*;

public class StatementCacheExample2 {

    // the TOTAL_NUM_ROWS constant is the total number of rows
    // that will be inserted
    public static final int TOTAL_NUM_ROWS = 2000;

    public static void main(String[] args) throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver( new oracle.jdbc.OracleDriver());

        // create a Connection object and connect to the database
        // as store_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection(  "jdbc:oracle:thin:@localhost:1523:ORCL",
                                                                "store_user",
                                                                "store_password"
                                                            );

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // delete the rows from the perf_test table
        myStatement.execute("TRUNCATE TABLE perf_test");

        // set the statement cache size to 1
        ((OracleConnection) myConnection).setStatementCacheSize(1);

        // display the statement cache size
        System.out.println("Statement cache size = " + ((OracleConnection) myConnection).getStatementCacheSize());

        // call insertRows() for the first time (as specified by
        // second parameter which is set to true)
        insertRows(myConnection, true);

        // delete the rows from the perf_test table
        myStatement.execute("TRUNCATE TABLE perf_test");

        // call insertRows() again
        insertRows(myConnection, false);

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    } // end of main()


    private static void insertRows( Connection myConnection, boolean firstTime ) throws SQLException {

        System.out.println("Inserting " + TOTAL_NUM_ROWS + " rows");

        // declare a PreparedStatement object
        PreparedStatement myPrepStatement;

        // if its the first time insertRows() was called, then
        // set the INSERT statement for the PreparedStatement;
        // otherwise, use the key to retrieve the previously
        // cached statement
        if (firstTime) {
            System.out.println("First time insertRows() was called: " + "creating a new statement");
            myPrepStatement = myConnection.prepareStatement( "INSERT INTO perf_test " + "(value) VALUES (?)" );
        } else {
            System.out.println("insertRows() has been called before: " + "using the explicitly cached statement");
            myPrepStatement = ((OracleConnection) myConnection).prepareStatementWithKey( "myCachedStatement" );
        }

        // get and display the creation state
        int creationState = ((OracleStatement) myPrepStatement).creationState();
        System.out.println("creationState = " + creationState);

        // display the meaning of the creation state
        switch (creationState) {
            case OracleStatement.NEW:
                System.out.println("New statement created");
                break;
            case OracleStatement.IMPLICIT:
                System.out.println("Using implicitly cached statement");
                break;
            case OracleStatement.EXPLICIT:
                System.out.println("Using explicitly cached statement");
                break;
        } // end of switch

        // insert the rows
        int count2 = 0;
        for (int count = 0; count < TOTAL_NUM_ROWS; count++) {
            myPrepStatement.setInt(1, count);
            myPrepStatement.execute();
        }

        // commit the SQL statements
        myConnection.commit();

        // close the PreparedStatement object using the
        // closeWithKey() method to set the key for the cached statement
        System.out.println("Closing the statement and setting the key");
        ((OracleStatement) myPrepStatement).closeWithKey( "myCachedStatement" );

    } // end of insertRows()

}