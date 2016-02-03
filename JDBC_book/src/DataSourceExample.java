import oracle.jdbc.pool.OracleDataSource;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Plamen Stilyianov
 * Date: 01-Feb-2008
 * Time: 16:27:29
 * To change this template use File | Settings | File Templates.
 */
public class DataSourceExample {

    public static void main(String args[]) throws SQLException {
        // step 1: create a connection pool data source object
        System.out.println("Creating an OracleConnectionPooDataSource " + "object");
        OracleDataSource myOCPDS = new OracleDataSource();

        // step 2: set the attributes for the physical database
        // connection using the connection pool data source object
        System.out.println("Setting the attributes of the " + "OracleConnectionPoolDataSource object");
        myOCPDS.setServerName("localhost");
        myOCPDS.setDatabaseName("ORCL");
        myOCPDS.setPortNumber(1523);
        myOCPDS.setDriverType("thin");
        myOCPDS.setUser("store_user");
        myOCPDS.setPassword("store_password");

        java.util.Properties prop = new java.util.Properties();
        prop.setProperty("MinLimit", "5"); // the cache size is 5 at least
        prop.setProperty("MaxLimit", "25");
        prop.setProperty("InitialLimit", "3"); // create 3 connections at startup
        prop.setProperty("InactivityTimeout", "1800"); // seconds
        prop.setProperty("AbandonedConnectionTimeout", "900"); // seconds
        prop.setProperty("MaxStatementsLimit", "10");
        prop.setProperty("PropertyCheckInterval", "60"); // seconds

        myOCPDS.setConnectionCacheProperties(prop); // set properties - Use Oracle Universal Connection Pool instead

        // step 4: request, use, and finally close a connection instance
        System.out.println("Requesting a Connection instance");
        Connection myConnection = myOCPDS.getConnection();
        DisplayCustomer(myConnection, 1);  // display customer #1
        System.out.println("Closing the Connection instance");
        myConnection.close();

        // repeat step 4 to display customer #2
        System.out.println("Requesting a Connection instance");
        myConnection = myOCPDS.getConnection();
        DisplayCustomer(myConnection, 2);  // display customer #2
        System.out.println("Closing the Connection instance");
        myConnection.close();

        Properties propList = myOCPDS.getConnectionCacheProperties(); // retrieve

        System.out.println(propList);

        // step 5: close the pooled connection object
        System.out.println("Closing the PooledConnection object");
        myOCPDS.close();

    } // end of main()


    public static void DisplayCustomer(Connection myConnection, int id) throws SQLException {

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // declare variables and objects used to represent
        // the column values retrieved from the customers table
        String firstName;
        String lastName;

        // create a ResultSet object
        ResultSet customerResultSet = myStatement.executeQuery( "SELECT first_name, last_name " +
                                                                "FROM customers " +
                                                                "WHERE id = " + id
                                                              );

        // retrieve and display the column values
        while (customerResultSet.next()) {
            firstName = customerResultSet.getString("first_name");
            lastName = customerResultSet.getString("last_name");

            System.out.println("id = " + id);
            System.out.println("firstName = " + firstName);
            System.out.println("lastName = " + lastName);
        } // end of while loop

        // close the ResultSet and Statement objects
        customerResultSet.close();
        myStatement.close();

    } // end of DisplayCustomer()


}
