/*
  JNDIExample1.java illustrates how to bind a data source
  to JNDI
*/

// import the required packages

import java.util.*;
import java.sql.*;

import oracle.jdbc.pool.*;

import javax.naming.*;

public class JNDIExample1 {

    public static void main(String args[]) throws SQLException, NamingException {

        // step 1: create an OracleDataSource object
        System.out.println("Creating an OracleDataSource object");
        OracleDataSource myODS = new OracleDataSource();

        // step 2: set the attributes for the OracleDataSource object
        System.out.println("Setting the attributes of the " + "OracleDataSource object");
        myODS.setServerName("localhost");
        myODS.setDatabaseName("ORCL");
        myODS.setPortNumber(1523);
        myODS.setDriverType("thin");
        myODS.setUser("store_user");
        myODS.setPassword("store_password");

        // step 3: create a Properties object
        System.out.println("Creating a Properties object");
        Properties myProperties = new Properties();

        // step 4: add the JNDI properties to the Properties object
        System.out.println("Adding the JNDI properties");
        myProperties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        myProperties.setProperty(Context.PROVIDER_URL, "file:C:/TEMP");

        // step 5: create a JNDI Context object
        System.out.println("Creating a JNDI Context object");
        Context myContext = new InitialContext(myProperties);

        // step 6: bind the OracleDataSource object to JNDI
        System.out.println("Binding the OracleDataSource object to " + "JNDI using the name 'myBoundODS'");
        myContext.bind("myBoundODS", myODS);

    } // end of main()

}