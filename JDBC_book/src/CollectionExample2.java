/*
  CollectionExample2.java shows how to access nested tables
  using using weakly typed ARRAY objects
*/

// import the required packages

import java.sql.*;

import oracle.sql.STRUCT;
import oracle.sql.ARRAY;
import oracle.sql.StructDescriptor;
import oracle.sql.ArrayDescriptor;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class CollectionExample2 {

    public static void insertCustomer(
            Connection myConnection
    ) throws SQLException {

        System.out.println("Inserting a customer into customers_with_nested_table");

        // step 1: create an ArrayDescriptor object for the collection
        // object type (in this case, COLLECTION_USER.NESTED_TABLE_ADDRESS_TYP)
        ArrayDescriptor addressArrayDescriptor =
                ArrayDescriptor.createDescriptor(
                        "COLLECTION_USER.NESTED_TABLE_ADDRESS_TYP", myConnection
                );

        // Step 2: create an Object array to store the elements for the
        // collection
        Object[] addressAttributes1 = new Object[4];
        addressAttributes1[0] = "1 Main Street";  // street
        addressAttributes1[1] = "Uptown";  // city
        addressAttributes1[2] = "NY";  // state
        addressAttributes1[3] = "55512";  // zip

        Object[] addressAttributes2 = new Object[4];
        addressAttributes2[0] = "2 Side Street";  // street
        addressAttributes2[1] = "Beantown";  // city
        addressAttributes2[2] = "MA";  // state
        addressAttributes2[3] = "12345";  // zip

        // create a StructDescriptor
        StructDescriptor addressDescriptor =
                StructDescriptor.createDescriptor(
                        "COLLECTION_USER.ADDRESS_TYP", myConnection
                );

        // create STRUCT objects for the addresses
        STRUCT addressSTRUCT1 =
                new STRUCT(addressDescriptor, myConnection, addressAttributes1);
        STRUCT addressSTRUCT2 =
                new STRUCT(addressDescriptor, myConnection, addressAttributes2);

        // Step 2: create an Object array to store the elements for the
        // collection
        Object[] addresses = {addressSTRUCT1, addressSTRUCT2};

        // step 3: create an ARRAY object
        ARRAY addressARRAY =
                new ARRAY(addressArrayDescriptor, myConnection, addresses);

        // step 4: use a prepared statement to insert the ARRAY
        // into the table
        PreparedStatement myPrepStatement =
                myConnection.prepareStatement(
                        "INSERT INTO customers_with_nested_table VALUES (?, ?, ?, ?)"
                );
        myPrepStatement.setInt(1, 2);
        myPrepStatement.setString(2, "Cynthia");
        myPrepStatement.setString(3, "Green");
        ((OraclePreparedStatement) myPrepStatement).setARRAY(
                4, addressARRAY
        );
        myPrepStatement.execute();
        myPrepStatement.close();

    } // end of insertCustomer()


    public static void displayCustomers(
            Connection myConnection,
            Statement myStatement
    ) throws SQLException {

        System.out.println("Customers from customers_with_nested_table:");

        // step 1: create a result set and use it to select
        // the collection, along with the other columns from the table
        ResultSet customerResultSet = myStatement.executeQuery(
                "SELECT id, first_name, last_name, addresses " +
                        "FROM customers_with_nested_table"
        );

        // step 2: while there are rows in the result set ...
        while (customerResultSet.next()) {

            System.out.println("id = " +
                    customerResultSet.getInt("id"));
            System.out.println("first_name = " +
                    customerResultSet.getString("first_name"));
            System.out.println("last_name = " +
                    customerResultSet.getString("last_name"));

            // a: retrieve the collection from the result set into an
            // ARRAY using the getARRAY() method
            ARRAY addressARRAY =
                    ((OracleResultSet) customerResultSet).getARRAY("addresses");

            // b: retrieve the element values from the ARRAY using the
            // getArray() method, storing the element values in an Object array
            Object[] addresses = (Object[]) addressARRAY.getArray();

            // c: read the element values from the Object array
            for (int count = 0; count < addresses.length; count++) {
                STRUCT addressSTRUCT = (STRUCT) addresses[count];
                Object[] addressAttributes = addressSTRUCT.getAttributes();
                System.out.println("street = " + addressAttributes[0]);
                System.out.println("city = " + addressAttributes[1]);
                System.out.println("state = " + addressAttributes[2]);
                System.out.println("zip = " + addressAttributes[3]);
            }

        } // end of while loop

        // step 3: close the result set
        customerResultSet.close();

    } // end of displayCustomers()


    public static void updateCustomerAddress(
            Connection myConnection,
            Statement myStatement,
            int id
    ) throws SQLException {

        System.out.println("Updating customer #" + id +
                "'s first address");

        // step 1: create a result set and use it to select
        // the collection (in this case, the addresses for a customer
        // with the specified id)
        ResultSet customerResultSet = myStatement.executeQuery(
                "SELECT addresses " +
                        "FROM customers_with_nested_table " +
                        "WHERE id = " + id
        );
        customerResultSet.next();

        // step 2: retrieve the original collection from the
        // result set into an ARRAY using the getARRAY() method
        ARRAY addressARRAY =
                ((OracleResultSet) customerResultSet).getARRAY("addresses");

        // step 3: close the result set
        customerResultSet.close();

        // step 4: retrieve the elements from the ARRAY into an
        // Object array using the getArray() method
        Object[] addresses = (Object[]) addressARRAY.getArray();

        // get the old address and StructDescriptor
        STRUCT oldAddressSTRUCT = (STRUCT) addresses[0];
        StructDescriptor addressDescriptor =
                oldAddressSTRUCT.getDescriptor();

        // set the new address attributes
        Object[] newAddressAttributes = new Object[4];
        newAddressAttributes[0] = "3 New Street";
        newAddressAttributes[1] = "Middle Town";
        newAddressAttributes[2] = "CA";
        newAddressAttributes[3] = "12345";

        // create a new address STRUCT
        STRUCT newAddressSTRUCT =
                new STRUCT(addressDescriptor, myConnection, newAddressAttributes);

        // use a prepared statement to perform the update
        PreparedStatement myPrepStatement =
                myConnection.prepareStatement(
                        "UPDATE TABLE (" +
                                "  SELECT addresses FROM customers_with_nested_table " +
                                "  WHERE id = ? " +
                                ") a " +
                                "SET VALUE(a) = ? " +
                                "WHERE VALUE(a) = ?"
                );
        myPrepStatement.setInt(1, id);
        myPrepStatement.setObject(2, newAddressSTRUCT);
        myPrepStatement.setObject(3, oldAddressSTRUCT);
        myPrepStatement.execute();
        myPrepStatement.close();

    } // end of updateCustomerAddress()


    public static void main(String args[])
            throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(
                new oracle.jdbc.OracleDriver()
        );

        // create a Connection object, and connect to the database
        // as collection_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1523:ORCL",
                "collection_user",
                "collection_password"
        );

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        // display the customers
        displayCustomers(myConnection, myStatement);

        // insert a customer
        insertCustomer(myConnection);

        // update customer #1's address
        updateCustomerAddress(myConnection, myStatement, 1);

        // display the customers
        displayCustomers(myConnection, myStatement);

        // rollback the changes made to the database
        myConnection.rollback();

        // close the Statement and Connection objects
        myStatement.close();
        myConnection.close();

    } // end of main()

}