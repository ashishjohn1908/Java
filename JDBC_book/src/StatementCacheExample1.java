/*
  StatementCacheExample1.java shows the use of
  implicit statement caching
*/

// import the JDBC packages

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleStatement;

import java.sql.*;

public class StatementCacheExample1 {

    // the TOTAL_NUM_ROWS constant is the total number of rows
    // that will be inserted
    public static final int TOTAL_NUM_ROWS = 2000;

    public static void main(String[] args) throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        // create a Connection object and connect to the database
        // as store_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1523:ORCL", "store_user", "store_password");

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // delete the rows from the perf_test table
        myStatement.execute("TRUNCATE TABLE perf_test");

        // set the statement cache size to 1
        ((OracleConnection) myConnection).setStmtCacheSize(1);

        // get and display the statement cache size
        int myStatementCacheSize = ((OracleConnection) myConnection).getStmtCacheSize();
        System.out.println("Statement cache size = " + myStatementCacheSize);

        // insert rows
        insertRows(myConnection);

        // delete the rows from the perf_test table
        myStatement.execute("TRUNCATE TABLE perf_test");

        // insert rows again (implicitly cached statement is used)
        insertRows(myConnection);

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    } // end of main()


    private static void insertRows(Connection myConnection) throws SQLException {

        System.out.println("Inserting " + TOTAL_NUM_ROWS + " rows");

        // create a PreparedStatement object
        PreparedStatement myPrepStatement = myConnection.prepareStatement("INSERT INTO perf_test " +
                "(value) VALUES (?)");

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

        // close the PreparedStatement object
        myPrepStatement.close();

    } // end of insertRows()

}