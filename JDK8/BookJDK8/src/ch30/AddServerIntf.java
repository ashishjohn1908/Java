package ch30;

/**
 * Created by plamen on 25/08/2014.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AddServerIntf extends Remote {
    double add(double d1, double d2) throws RemoteException;
}
