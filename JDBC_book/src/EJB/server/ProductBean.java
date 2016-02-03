/*
  ProductBean.java defines the class that implements the methods
  defined in the ProductRemote class, plus the other standard methods
  that are required for a session bean
*/

package EJB.server;


import java.sql.*;
import javax.sql.*;

import java.rmi.RemoteException;
import javax.ejb.*;
import javax.naming.*;

public class ProductBean implements SessionBean {

    // declare the Connection attribute
    transient Connection myConnection;


    // define the ejbCreate() method
    public void ejbCreate() throws CreateException {

        try {

            // look up the data source and get the connection
            // to the database
            InitialContext myInitialContext = new InitialContext();
            DataSource myDataSource = (DataSource) (myInitialContext.lookup("jdbc/OracleCoreDS"));
            myConnection = myDataSource.getConnection();

        } catch (Exception e) {

            e.printStackTrace();
            throw new javax.ejb.CreateException( "Couldn't create JDBC connection" );

        }

    }

    // define the query() method that retrieves the name, description,
    // and price columns for a row in the products table with a
    // specified id, and returns a Product object
    public Product query(int id) throws SQLException, RemoteException {

        // declare a PreparedStatement object and a ResultSet object
        PreparedStatement myPrepStatement = null;
        ResultSet myResultSet = null;
        Product myProduct;
        try {

            // prepare the SELECT statement that will retrieve the name,
            // description, and price columns for the specified row from
            // the products table
            myPrepStatement = myConnection.prepareStatement( "SELECT name, description, price " + "FROM products " +
                                                             "WHERE id = ?"
                                                            );

            // bind the id parameter to the PreparedStatement object
            // using the setInt() method
            myPrepStatement.setInt(1, id);

            // execute the query
            myResultSet = myPrepStatement.executeQuery();

            // if no row was retrieved then throw a RemoteException object
            if (!myResultSet.next()) {
                throw new RemoteException("No product with id of " + id);
            }

            // get the name, description, and price column values
            String name = myResultSet.getString("name");
            String description = myResultSet.getString("description");
            float price = myResultSet.getFloat("price");

            // create and return a new Product object
            myProduct = new Product(id, name, description, price);

            return myProduct;

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                // close the PreparedStatement object
                if (myPrepStatement != null) {
                    myPrepStatement.close();
                }

                // close the ResultSet object
                if (myResultSet != null) {
                    myResultSet.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return null;

    }


    // define stubs for the other bean methods
    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }

    public void ejbRemove() {
    }

    public void setSessionContext(SessionContext sessionContext) {
    }

}