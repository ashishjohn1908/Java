/*
  ConnectionPoolExample2.java illustrates connection pooling
  with the OCI driver
*/

// import the required packages

import oracle.jdbc.oci.OracleOCIConnection;
import oracle.jdbc.pool.OracleOCIConnectionPool;

import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;

public class ConnectionPoolExample2 {


    public static void main(String args[]) throws SQLException {

        // create an OracleOCIConnectionPool object named myOOCP
        System.out.println("Creating an OracleOCIConnectionPool object " + "named myOOCP");
        OracleOCIConnectionPool myOOCP = new OracleOCIConnectionPool();

        // set the attributes for the physical database connections
        System.out.println("Setting the attributes of myOOCP");
        myOOCP.setServerName("localhost");
        myOOCP.setDatabaseName("ORCL");
        myOOCP.setDriverType("oci8");
        myOOCP.setPortNumber(1523);
        myOOCP.setUser("store_user");
        myOOCP.setPassword("store_password");

        // set the values for the dynamic attributes of myOOCP
        System.out.println("Setting the dynamic attribute values of " + "myOOCP");
        Properties myProperties = new Properties();
        myProperties.put(OracleOCIConnectionPool.CONNPOOL_MIN_LIMIT, "5");
        myProperties.put(OracleOCIConnectionPool.CONNPOOL_MAX_LIMIT, "10");
        myProperties.put(OracleOCIConnectionPool.CONNPOOL_INCREMENT, "2");
        myProperties.put(OracleOCIConnectionPool.CONNPOOL_TIMEOUT, "30");
        myProperties.put(OracleOCIConnectionPool.CONNPOOL_NOWAIT, "true");
        myOOCP.setPoolConfig(myProperties);

        // display the dynamic attributes values of myOOCP
        System.out.println("myOOCP.getMinLimit() = " + myOOCP.getMinLimit());
        System.out.println("myOOCP.getMaxLimit() = " + myOOCP.getMaxLimit());
        System.out.println("myOOCP.getConnectionIncrement() = " + myOOCP.getConnectionIncrement());
        System.out.println("myOOCP.getTimeout() = " + myOOCP.getTimeout());

        System.out.println("myOOCP.getNoWait() = " + myOOCP.getNoWait());

        // request a connection instance from myOOCP and store
        // the connection instance in myConnection
        System.out.println("Requesting connection instance from myOOCP");
        OracleOCIConnection myConnection = (OracleOCIConnection) myOOCP.getConnection();

        // display the values returned by the getPoolSize() and
        // getActiveSize() methods
        System.out.println("myOOCP.getPoolSize() = " + myOOCP.getPoolSize());
        System.out.println("myOOCP.getActiveSize() = " + myOOCP.getActiveSize());

        // close myConnection and myOOCP using the close() method
        System.out.println("Closing connection instance and myOOCP");
        myConnection.close();
        myOOCP.close();

    } // end of main()

}