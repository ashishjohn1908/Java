/*
  CollectionExample1.java shows how to access varrays
  using using weakly typed ARRAY objects
*/

// import the required packages

import java.sql.*;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

public class CollectionExample1 {

    public static void insertCustomer(
            Connection myConnection
    ) throws SQLException {

        System.out.println("Inserting a customer into customers_with_varray");

        // step 1: create an ArrayDescriptor object for the collection
        // object type (in this case, COLLECTION_USER.VARRAY_ADDRESS_TYP)
        ArrayDescriptor addressDescriptor =
                ArrayDescriptor.createDescriptor(
                        "COLLECTION_USER.VARRAY_ADDRESS_TYP", myConnection
                );

        // step 2: create an Object array to store the elements for the
        // collection
        Object[] addressElements = {
                "1 Main Street, Uptown, NY, 55512",
                "2 Side Street, Beantown, MA, 12345"
        };

        // step 3: create an ARRAY object
        ARRAY addressARRAY =
                new ARRAY(addressDescriptor, myConnection, addressElements);

        // step 4: use a prepared statement to insert the ARRAY
        // into the table, along with the other columns required
        // for the new row
        PreparedStatement myPrepStatement =
                myConnection.prepareStatement(
                        "INSERT INTO customers_with_varray VALUES (?, ?, ?, ?)"
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

        System.out.println("Customers from customers_with_varray:");

        // step 1: create a result set and use it to select
        // the rows from customers_with_varray
        ResultSet customerResultSet = myStatement.executeQuery(
                "SELECT id, first_name, last_name, addresses " +
                        "FROM customers_with_varray"
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
                System.out.println("addresses[" + count + "] = " +
                        addresses[count]);
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
                        "FROM customers_with_varray " +
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

        // step 5: change the first element in the Object array
        addresses[0] = "3 New Street, Middle Town, CA, 12345";

        // step 6: retrieve the ArrayDescriptor from the ARRAY using the
        // getDescriptor() method
        ArrayDescriptor addressDescriptor = addressARRAY.getDescriptor();

        // step 7: create a new ARRAY object
        ARRAY newAddressARRAY =
                new ARRAY(addressDescriptor, myConnection, addresses);

        // step 8: use a prepared statement to perform the update
        PreparedStatement myPrepStatement =
                myConnection.prepareStatement(
                        "UPDATE customers_with_varray " +
                                "SET addresses = ? " +
                                "WHERE id = ?"
                );
        ((OraclePreparedStatement) myPrepStatement).setARRAY(
                1, newAddressARRAY
        );
        myPrepStatement.setInt(2, id);
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

        // display the customers in the customers_with_varray table
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