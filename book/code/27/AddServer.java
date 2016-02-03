//listing 19

import java.net.*;
import java.rmi.*;

public class AddServer {
    public static void main(String args[]) {
        try {
            AddServerImpl addServerImpl = new AddServerImpl();
            Naming.rebind("AddServer1", addServerImpl);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}