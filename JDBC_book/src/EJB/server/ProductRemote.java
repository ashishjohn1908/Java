/*
  ProductRemote.java defines the remote interface for the bean
*/

package EJB.server;

// import the required classes

import java.rmi.RemoteException;
import javax.ejb.EJBObject;
import java.sql.SQLException;

public interface ProductRemote extends EJBObject {

    // the query() method is defined in the ProductBean class;
    // the method retrieves the name, description, and price columns
    // from the row in the products table that has the specified id
    // and returns a new Product object
    public Product query(int id) throws SQLException, RemoteException;

}