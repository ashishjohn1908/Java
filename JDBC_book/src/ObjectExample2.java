/*
  ObjectExample2.java shows how to access object references using
  weakly typed REF objects
*/

// import the required packages

import oracle.sql.REF;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import java.math.BigDecimal;
import java.sql.*;

public class ObjectExample2 {

    public static void displayPurchase(
            Connection myConnection,
            Statement myStatement
    ) throws SQLException {

        System.out.println("Purchase from the purchases table:");

        // step 1: create a result set and select the object
        // references from the REF columns
        ResultSet purchaseResultSet = myStatement.executeQuery(
                "SELECT customer, product " +
                        "FROM purchases"
        );
        purchaseResultSet.next();

        // step 2: retrieve the object references from the result set
        // using the getRef() method; this method returns a java.sql.Ref
        // object that is cast to an oracle.sql.REF object
        REF customerRef = (REF) purchaseResultSet.getRef("customer");
        REF productRef = (REF) purchaseResultSet.getRef("product");

        // step 3: close the result set
        purchaseResultSet.close();

        // step 4: retrieve the actual objects from the REFs using
        // the getValue() method; this method returns an Object that
        // is cast to a STRUCT, which is then stored
        STRUCT customer = (STRUCT) customerRef.getValue();
        STRUCT product = (STRUCT) productRef.getValue();

        // retrieve the attribute values from the STRUCTs using
        // the getAttributes() method, storing them in an Object array
        Object[] productAttributes = product.getAttributes();
        Object[] customerAttributes = customer.getAttributes();

        // read the attribute values from the Object array for
        // the product
        System.out.println("Product:");
        System.out.println("id = " + productAttributes[0]);
        System.out.println("name = " + productAttributes[1]);
        System.out.println("description = " + productAttributes[2]);
        System.out.println("price = " + productAttributes[3]);
        System.out.println("days_valid = " + productAttributes[4]);

        // read the attribute values from the Object array for
        // the customer
        System.out.println("Customer:");
        System.out.println("id = " + customerAttributes[0]);
        System.out.println("first_name = " + customerAttributes[1]);
        System.out.println("last_name = " + customerAttributes[2]);
        System.out.println("dob = " + customerAttributes[3]);
        System.out.println("phone = " + customerAttributes[4]);

        // get the customer address
        STRUCT address = (STRUCT) customerAttributes[5];
        Object[] addressAttributes = address.getAttributes();
        System.out.println("street = " + addressAttributes[0]);
        System.out.println("city = " + addressAttributes[1]);
        System.out.println("state = " + addressAttributes[2]);
        System.out.println("zip = " + addressAttributes[3]);

    } // end of displayPurchase()


    public static void updateProductPurchased(
            Connection myConnection,
            Statement myStatement,
            int purchaseId,
            int productId
    ) throws SQLException {

        System.out.println("Updating purchase #" + purchaseId +
                " with product #" + productId);

        // step 1: create a result set and use it to
        // select the new object reference
        ResultSet productResultSet = myStatement.executeQuery(
                "SELECT REF(op) " +
                        "FROM object_products op " +
                        "WHERE op.id = " + productId
        );
        productResultSet.next();

        // step 2: retrieve the object reference from the result set
        // using the getRef() method, storing it in a REF object
        REF productRef = (REF) productResultSet.getRef(1);

        // step 3: close the result set
        productResultSet.close();

        // step 4: use a prepared statement to perform the update of
        // the existing object reference stored in the REF column
        // with the new object reference
        PreparedStatement myPrepStatement =
                myConnection.prepareStatement(
                        "UPDATE purchases " +
                                "SET product = ? " +
                                "WHERE id = ?"
                );
        myPrepStatement.setRef(1, productRef);  // new object reference
        myPrepStatement.setInt(2, purchaseId);
        myPrepStatement.execute();
        myPrepStatement.close();

    } // end of updateProductPurchased()


    public static void updateProduct(
            Connection myConnection,
            Statement myStatement,
            int productId
    ) throws SQLException {

        System.out.println("Updating product #" + productId +
                "'s description and price");

        // step 1: create a result set and use it to select
        // the object reference
        ResultSet productResultSet = myStatement.executeQuery(
                "SELECT REF(op) " +
                        "FROM object_products op " +
                        "WHERE op.id = " + productId
        );
        productResultSet.next();

        // step 2: retrieve the object reference from the result set
        // using the getRef() method, storing it in a REF object
        REF productRef = (REF) productResultSet.getRef(1);

        // step 3: close the result set
        productResultSet.close();

        // step 4: retrieve the object from the REF using
        // the getValue()method, storing it in a STRUCT object
        STRUCT product = (STRUCT) productRef.getValue();

        // step 5: retrieve the object attributes from the STRUCT
        // using the getAttributes() method, storing them in an
        // Object array
        Object[] productAttributes = product.getAttributes();

        // step 6: change the object attributes in the Object array
        productAttributes[2] = "25 oz box of sardines";  // description
        productAttributes[3] = new BigDecimal(3.49);  // price

        // step 7: retrieve the StructDescriptor from your object
        // using the getDescriptor() method
        StructDescriptor productDescriptor = product.getDescriptor();

        // step 8: create a new STRUCT object
        STRUCT updatedProduct =
                new STRUCT(productDescriptor, myConnection, productAttributes);

        // step 9: update the object in the database using the setValue()
        // method for the REF
        productRef.setValue(updatedProduct);

    } // end of updateProduct()


    public static void main(String args[])
            throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(
                new oracle.jdbc.OracleDriver()
        );

        // create a Connection object, and connect to the database
        // as object_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1523:ORCL",
                "object_user",
                "object_password"
        );

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // display the purchases
        displayPurchase(myConnection, myStatement);

        // update product #2's description and price attributes
        updateProduct(myConnection, myStatement, 2);

        // update purchase #1 to reference product #2
        updateProductPurchased(myConnection, myStatement, 1, 2);

        // display the purchases
        displayPurchase(myConnection, myStatement);

        // rollback the changes made to the database
        myConnection.rollback();

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    } // end of main()

}