/*
  ConnectionCacheExample2.java illustrates connection caching and
  shows how to control the number of PooledConnection objects
  created in the connection cache. The connection cache featured
  in this program uses the fixed with no wait scheme.
*/

// import the JDBC packages

import java.sql.*;

import oracle.jdbc.pool.OracleConnectionCacheImpl;
import oracle.jdbc.dcn.DatabaseChangeEvent;

public class ConnectionCacheExample2 {

    public static void main(String args[])
            throws SQLException {

        // create an OracleConnectionCacheImpl object
        System.out.println("Creating an OracleConnectionCacheImpl " + "object");
        OracleConnectionCacheImpl myOCCI = new OracleConnectionCacheImpl();

        // set the attributes for the physical database connections
        System.out.println("Setting the attributes of the OracleConnectionCacheImpl object");
        myOCCI.setServerName("localhost");
        myOCCI.setDatabaseName("ORCL");
        myOCCI.setPortNumber(1523);
        myOCCI.setDriverType("thin");
        myOCCI.setUser("store_user");
        myOCCI.setPassword("store_password");

        // set the connection cache scheme to fixed return null
        System.out.println("Setting cache scheme to fixed with no wait");
        myOCCI.setCacheScheme(OracleConnectionCacheImpl.FIXED_RETURN_NULL_SCHEME);

        // display the default maximum limit of PooledConnection objects
        // using the getMaxLimit() method
        System.out.println("myOCCI.getMaxLimit() = " + myOCCI.getMaxLimit());

        // change the maximum limit of PooledConnection objects
        int numPooledConnections = 5;
        System.out.println("Setting maximum limit of PooledConnection " + "objects to " + numPooledConnections);
        myOCCI.setMaxLimit(numPooledConnections);
        System.out.println("myOCCI.getMaxLimit() = " + myOCCI.getMaxLimit());

        // create an array of Connection objects
        Connection[] myConnectionArray = new Connection[numPooledConnections];

        // request all the Connection instances from myOCCI and
        // store them in myConnectionArray
        for (int loopCount = 0; loopCount < numPooledConnections;
             loopCount++) {

            System.out.println("Requesting a Connection instance, " + "storing it in myConnectionArray[" + loopCount + "]");
            myConnectionArray[loopCount] = myOCCI.getConnection();

        } // end of for loop

        // request another connection instance from myOCCI
        // and store the connection instance in myConnection;
        // since the maximum number of PooledConnection objects already
        // exist in myOCCI, what happens next depends on the connection
        // cache scheme in use - in this program, the fixed with no wait
        // scheme is used, which means null is returned when another
        // connection instance is requested
        System.out.println("Requesting another Connection instance, " + "storing it in myConnection");
        Connection myConnection = myOCCI.getConnection();
        if (myConnection != null) {
            System.out.println("myConnection is valid (not null)");
        } else {
            System.out.println("myConnection is invalid (null)");
        }

        // close the OracleConnectionCacheImpl object
        System.out.println("Closing the OracleConnectionCacheImpl " + " object");
        myOCCI.close();

    } // end of main()

}