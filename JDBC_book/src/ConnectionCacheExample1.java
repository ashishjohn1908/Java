/*
  ConnectionCacheExample1.java illustrates connection caching
*/

// import the JDBC packages

import java.sql.*;

import oracle.jdbc.pool.OracleConnectionCacheImpl;

public class ConnectionCacheExample1 {

    public static void main(String args[])
            throws SQLException {

        // create an OracleConnectionCacheImpl object
        System.out.println("Creating an OracleConnectionCacheImpl " +
                "object");
        OracleConnectionCacheImpl myOCCI = new OracleConnectionCacheImpl();

        // set the attributes for the physical database connections
        System.out.println("Setting the attributes of the OracleConnectionCacheImpl object");
        myOCCI.setServerName("localhost");
        myOCCI.setDatabaseName("ORCL");
        myOCCI.setPortNumber(1523);
        myOCCI.setDriverType("thin");
        myOCCI.setUser("store_user");
        myOCCI.setPassword("store_password");

        // request a connection instance from myOCCI and store
        // the connection instance in myConnection
        System.out.println("Requesting a Connection instance and storing it in myConnection");
        Connection myConnection = myOCCI.getConnection();

        // display the values returned by the getActiveSize() and
        // getCacheSize() methods; both methods return 1 at this point,
        // since there is one PooledConnection object
        // in the cache, and whose connection instance is in use
        System.out.println("myOCCI.getActiveSize() = " + myOCCI.getActiveSize());
        System.out.println("myOCCI.getCacheSize() = " + myOCCI.getCacheSize());

        // keep myConnection open, so that further requests for
        // connection instances require myOCCI to create a new
        // PooledConnection object in the cache
        // myConnection.close();

        // request another connection instance from myOCCI
        // and store the connection instance in myConnection2
        System.out.println("Requesting another Connection instance " +
                "and storing it in myConnection2");
        Connection myConnection2 = myOCCI.getConnection();

        // display the values returned by the getActiveSize() and
        // getCacheSize() methods; both methods return 2 at this point,
        // since there are two PooledConnection objects
        // in the cache, both of whose connection instances are in use
        System.out.println("myOCCI.getActiveSize() = " +
                myOCCI.getActiveSize());
        System.out.println("myOCCI.getCacheSize() = " +
                myOCCI.getCacheSize());

        // close myConnection2 using the close() method, this frees the
        // connection instance used by myConnection2
        System.out.println("Closing myConnection2");
        myConnection2.close();

        // request another connection instance from myOCCI
        // and store the connection instance in myConnection3
        System.out.println("Requesting another Connection instance " +
                "and storing it in myConnection3");
        Connection myConnection3 = myOCCI.getConnection();

        // display the values returned by the getActiveSize() and
        // getCacheSize() methods; both return 2 at this point,
        // because myConnection is still in use and
        // myConnection3 uses the connection instance that was freed
        // when myConnection2 was closed earlier
        System.out.println("myOCCI.getActiveSize() = " +
                myOCCI.getActiveSize());
        System.out.println("myOCCI.getCacheSize() = " +
                myOCCI.getCacheSize());

        // close myConnection3 using the close() method
        System.out.println("Closing myConnection3");
        myConnection3.close();

        // display the values returned by the getActiveSize() and
        // getCacheSize() methods; getActiveSize() returns 1 at this
        // point, since the connection instance used by myConnection3
        // is now free; getCacheSize() still returns 2 since there
        // are still two PooledConnection objects in the cache
        System.out.println("myOCCI.getActiveSize() = " +
                myOCCI.getActiveSize());
        System.out.println("myOCCI.getCacheSize() = " +
                myOCCI.getCacheSize());

        // close the OracleConnectionCacheImpl object
        System.out.println("Closing the OracleConnectionCacheImpl " +
                "object");
        myOCCI.close();

        // display the values returned by the getActiveSize() and
        // getCacheSize() methods; both methods return 0 at this point,
        // since both PooledConnection objects have been closed
        System.out.println("myOCCI.getActiveSize() = " +
                myOCCI.getActiveSize());
        System.out.println("myOCCI.getCacheSize() = " +
                myOCCI.getCacheSize());

    } // end of main()

}