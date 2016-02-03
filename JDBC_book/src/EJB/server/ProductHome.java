/*
  ProductHome.java defines the home interface for the bean
*/

package EJB.server;

// import the required classes

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

public interface ProductHome extends EJBHome {
    public ProductRemote create() throws CreateException, RemoteException;
}