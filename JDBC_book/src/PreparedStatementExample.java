/*
  BasicExample2.java shows how to use prepared SQL statements
*/

// import the JDBC packages

import java.sql.*;


public class PreparedStatementExample {

    private static class Product {
        int id;
        int typeId;
        String name;
        String description;
        double price;
    }

    public static void main(String args[]) throws SQLException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

        // create a Connection object, and connect to the database
        // as store_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1523:ORCL",
                                                                "store_user",
                                                                "store_password"
                                                             );

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        Product[] product_array = new Product[5];
        for (int counter = 0; counter < product_array.length; counter++) {
            product_array[counter] = new Product();
            product_array[counter].id = counter + 20;
            product_array[counter].typeId = 1;
            product_array[counter].name = "Test product";
            product_array[counter].description = "Test product";
            product_array[counter].price = 19.95;
        } // end of for loop

        // create a PreparedStatement object
        PreparedStatement myPrepStatement = myConnection.prepareStatement( "INSERT INTO products " +
                                                                            "(id, type_id, name, description, price) VALUES (" +
                                                                            "seq_products.nextval, ?, ?, ?, ?" +
                                                                            ")"
                                                                         );

        // initialize the values for the new rows using the
        // appropriate set methods
        for (int counter = 0; counter < product_array.length; counter++) {
            //  myPrepStatement.setInt(1, product_array[counter].id);
            myPrepStatement.setInt(1, product_array[counter].typeId);
            myPrepStatement.setString(2, product_array[counter].name);
            myPrepStatement.setString(3, product_array[counter].description);
            myPrepStatement.setDouble(4, product_array[counter].price);
            myPrepStatement.execute();
        } // end of for loop

        // close the PreparedStatement object
        myPrepStatement.close();

        // retrieve the id, type_id, name, description, and
        // price columns for these new rows using a ResultSet object
        Statement myStatement = myConnection.createStatement();
        ResultSet productResultSet = myStatement.executeQuery("SELECT id, type_id, name, description, price " +
                                                                "FROM products " +
                                                                "WHERE id > 12"
                                                             );

        // display the column values
        while (productResultSet.next()) {
            System.out.println("id = " + productResultSet.getInt("id"));
            System.out.println("type_id = " + productResultSet.getInt("type_id"));
            System.out.println("name = " + productResultSet.getString("name"));
            System.out.println("description = " + productResultSet.getString("description"));
            System.out.println("price = " + productResultSet.getDouble("price"));
        } // end of while loop

        // close this ResultSet object using the close() method
        productResultSet.close();

        // rollback the changes made to the database
        myConnection.rollback();

        // close the other JDBC objects
        myStatement.close();
        myConnection.close();

    } // end of main()

}