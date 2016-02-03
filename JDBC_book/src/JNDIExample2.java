/*
  JNDIExample.java illustrates how to lookup a data source
  using JNDI, and uses that data source to connect to
  the database
*/

// import the required packages

import java.util.*;
import java.sql.*;

import oracle.jdbc.pool.*;

import javax.naming.*;

public class JNDIExample2 {

    public static void main(String args[]) throws SQLException, NamingException {

        // step 1: create a Properties object
        System.out.println("Creating a Properties object");
        Properties myProperties = new Properties();

        // step 2: add the JNDI properties to the Properties object
        System.out.println("Adding the JNDI properties");
        myProperties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        myProperties.setProperty(Context.PROVIDER_URL,"file:C:/TEMP");

        // step 3: create a JNDI Context object
        System.out.println("Creating a JNDI Context object");
        Context myContext = new InitialContext(myProperties);

        // step 4: lookup the data source using JNDI
        System.out.println("Looking up the data source with " + "the name 'myBoundODS' using JNDI");
        OracleDataSource myODS = (OracleDataSource) myContext.lookup("myBoundODS");

        // request, use, and finally close a Connection object
        // from the OracleDataSource object
        System.out.println("Requesting a connection from the " + "OracleDataSource object");
        Connection myConnection = myODS.getConnection();
        DisplayCustomer(myConnection, 1);  // display customer #1
        myConnection.close();

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