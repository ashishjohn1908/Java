/*
  ObjectExample1.java shows how to access database objects using
  weakly typed STRUCT objects
*/

// import the required packages

import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import java.math.BigDecimal;
import java.sql.*;

public class ObjectExample1 {

    public static void insertProduct(
            Connection myConnection
    ) throws SQLException {

        System.out.println("Inserting a product");

        // step 1: create a StructDescriptor object for the database
        // object type (in this case, OBJECT_USER.PRODUCT_TYP)
        StructDescriptor productDescriptor = StructDescriptor.createDescriptor("OBJECT_USER.PRODUCT_TYP", myConnection);

        // step 2: create an Object array to store the attributes for
        // the new database object (in this case, a new product)
        Object[] productAttributes = new Object[5];
        productAttributes[0] = new BigDecimal(3);  // id
        productAttributes[1] = "Chips";  // name
        productAttributes[2] = "10 oz bag of chips";  // description
        productAttributes[3] = new BigDecimal(0.99);  // price
        productAttributes[4] = new BigDecimal(20);  // days_valid

        // step 3: create a STRUCT object
        STRUCT product =
                new STRUCT(productDescriptor, myConnection, productAttributes);

        // step 4: use a prepared statement to insert the STRUCT
        // into the table
        PreparedStatement myPrepStatement = myConnection.prepareStatement("INSERT INTO object_products VALUES (?)");
        myPrepStatement.setObject(1, product);
        myPrepStatement.execute();
        myPrepStatement.close();

    } // end of insertProduct()


    public static void displayProducts(Connection myConnection, Statement myStatement) throws SQLException {

        System.out.println("Products from the object_products table:");

        // step 1: create a result set and use it to select
        // the objects
        ResultSet productResultSet = myStatement.executeQuery("SELECT VALUE(op) FROM object_products op");

        // step 2: while there are objects in the result set ...
        while (productResultSet.next()) {

            // a: retrieve each database object from the result set
            // using the getObject() method, casting the returned Object
            // to a STRUCT
            STRUCT product = (STRUCT) productResultSet.getObject(1);

            // b: retrieve the attribute values from the STRUCT using
            // the getAttributes() method, storing them in an Object array
            Object[] productAttributes = product.getAttributes();

            // c: read the attribute values from the Object array
            System.out.println("id = " + productAttributes[0]);
            System.out.println("name = " + productAttributes[1]);
            System.out.println("description = " + productAttributes[2]);
            System.out.println("price = " + productAttributes[3]);
            System.out.println("days_valid = " + productAttributes[4]);

            // create a CallableStatement object and use it to call the
            // get_sell_by_date() function
            CallableStatement myCallableStatement = myConnection.prepareCall("{? = call product_typ.get_sell_by_date(?)}");
            myCallableStatement.registerOutParameter(1, Types.DATE);
            myCallableStatement.setObject(2, product);
            myCallableStatement.execute();
            System.out.println("sell by date = " + myCallableStatement.getDate(1));
            myCallableStatement.close();

        } // end of while loop

        // step 3: close the result set
        productResultSet.close();

    } // end of displayProducts()


    public static void updateProduct(Connection myConnection, Statement myStatement, int id) throws SQLException {

        System.out.println("Updating product #" + id + "'s description and price");

        // step 1: create a result set and use it to select
        // the object (in this case, the product with the specified id)
        ResultSet productResultSet = myStatement.executeQuery("SELECT VALUE(op) " +
                "FROM object_products op " +
                "WHERE op.id = " + id
        );
        productResultSet.next();

        // step 2: retrieve the original database object from the
        // result set into a STRUCT using the getObject() method,
        // casting the returned Object to a STRUCT
        STRUCT product = (STRUCT) productResultSet.getObject(1);

        // step 3: close the result set
        productResultSet.close();

        // step 4: retrieve the attributes from the STRUCT into an
        // Object array using the getAttributes() method
        Object[] productAttributes = product.getAttributes();

        // step 5: change the attributes in the Object array
        productAttributes[2] = "25 oz box of sardines";  // description
        productAttributes[3] = new BigDecimal(3.49);  // price

        // step 6: retrieve the StructDescriptor using the
        // getDescriptor() method
        StructDescriptor productDescriptor = product.getDescriptor();

        // step 7: create a new STRUCT object
        STRUCT updatedProduct = new STRUCT(productDescriptor, myConnection, productAttributes);

        // step 8: use a prepared statement to perform the update
        PreparedStatement myPrepStatement = myConnection.prepareStatement("UPDATE object_products op " +
                "SET VALUE(op) = ? " +
                "WHERE op.id = ?"
        );
        myPrepStatement.setObject(1, updatedProduct);
        myPrepStatement.setInt(2, id);
        myPrepStatement.execute();
        myPrepStatement.close();

    } // end of updateProduct()


    public static void deleteProduct(Connection myConnection, Statement myStatement, int id) throws SQLException {

        System.out.println("Deleting product #" + id + " from the object_products table");

        // delete a product from the object_products table
        myStatement.execute("DELETE FROM object_products op " +
                "WHERE op.id = " + id
        );

    } // end of deleteProduct()


    public static void main(String args[]) throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        // create a Connection object, and connect to the database
        // as object_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1523:ORCL",
                "object_user",
                "object_password"
        );

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // display the products in the object_products table
        displayProducts(myConnection, myStatement);

        // insert a product
        insertProduct(myConnection);

        // update product #2's description and price attributes
        updateProduct(myConnection, myStatement, 2);

        // delete product #1
        deleteProduct(myConnection, myStatement, 1);

        // display the products
        displayProducts(myConnection, myStatement);

        // rollback the changes made to the database
        myConnection.rollback();

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    } // end of main()

}