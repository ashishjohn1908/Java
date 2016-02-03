package EJB.client;/*
  Client.java is a stand-alone Java application that
  creates an instance of the bean
*/

// import the bean classes

import EJB.server.ProductHome;
import EJB.server.ProductRemote;
import EJB.server.Product;

// import the Java Naming and Directory Interface (JNDI) classes
import javax.naming.Context;
import javax.naming.InitialContext;

public class Client {

    public static void main(String args[]) throws Exception {

        // the first argument in the args array is the id of the product
        int id = Integer.valueOf(args[0]).intValue();

        // create a JNDI Context object
        Context myInitialContext = new InitialContext();

        // create a ProductHome object and use the lookup() method
        // of the Context object to find the bean
        ProductHome myProductHome = (ProductHome)
                myInitialContext.lookup("java:comp/env/JDBCEJB");

        // create a ProductRemote object by calling the create() method
        ProductRemote myProductRemote = myProductHome.create();

        // create a Product object and use the query() method of the
        // ProductRemote object to get the column values from the products
        // table and copy those values to the Product object
        // - the parameter to the query method is the id of the row in
        // the products table to be read
        Product myProduct = myProductRemote.query(id);

        // if the Product object is not null (i.e. the row was found in
        // the products table), then display the Product object's
        // attributes
        if (myProduct != null) {

            // display the Product object's variables
            System.out.println("myProduct details:");
            System.out.println("myProduct.id = " + myProduct.id);
            System.out.println("myProduct.name = " + myProduct.name);
            System.out.println("myProduct.description = " +
                    myProduct.description);
            System.out.println("myProduct.price = " + myProduct.price);

        } else {

            System.out.println("Product not found");

        }
    }
}