/*
  BasicExample3.java shows how to use the Oracle JDBC extensions
  to add a row to the customers table, and then retrieve that row
*/

// import the JDBC packages

import java.sql.*;

// import the Oracle JDBC extension packages
import oracle.sql.*;
import oracle.jdbc.*;

public class BasicExample3 {

    public static void main(String args[]) {

        try {
            // register the Oracle JDBC drivers
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            // create a Connection object, and connect to the database
            // as store_user using the Oracle JDBC Thin driver
            Connection myConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "store_user", "store_password");

            // disable auto-commit mode
            myConnection.setAutoCommit(false);

            // create an oracle.sql.NUMBER object
            oracle.sql.NUMBER id = new oracle.sql.NUMBER(6);
            int idInt = id.intValue();
            System.out.println("idInt = " + idInt);

            // create two oracle.sql.CHAR objects
            oracle.sql.CharacterSet myCharSet = CharacterSet.make(CharacterSet.US7ASCII_CHARSET);
            oracle.sql.CHAR firstName = new oracle.sql.CHAR("Jason", myCharSet);
            String firstNameString = firstName.stringValue();
            System.out.println("firstNameString = " + firstNameString);
            oracle.sql.CHAR lastName = new oracle.sql.CHAR("Price", myCharSet);
            System.out.println("lastName = " + lastName);

            // create an oracle.sql.DATE object
            oracle.sql.DATE dob = new oracle.sql.DATE("1969-02-01 13:54:12");
            String dobString = dob.stringValue();
            System.out.println("dobString = " + dobString);

            // create an OraclePreparedStatement object
            OraclePreparedStatement myPrepStatement = (OraclePreparedStatement) myConnection.prepareStatement("INSERT INTO customers " +
                    "(id, first_name, last_name, dob, phone) VALUES (" + "?, ?, ?, ?, ?" + ")" );

            // bind the objects to the OraclePreparedStatement using the
            // appropriate set methods
            myPrepStatement.setNUMBER(1, id);
            myPrepStatement.setCHAR(2, firstName);
            myPrepStatement.setCHAR(3, lastName);
            myPrepStatement.setDATE(4, dob);

            // set the phone column to NULL
            myPrepStatement.setNull(5, OracleTypes.CHAR);

            // run the PreparedStatement
            myPrepStatement.execute();
            System.out.println("Added row to customers table");

            // retrieve the ROWID, id, first_name, last_name, dob, and
            // phone columns for this new row using an OracleResultSet object
            Statement myStatement = myConnection.createStatement();
            OracleResultSet customerResultSet = (OracleResultSet) myStatement.executeQuery("SELECT ROWID, id, first_name, last_name, dob, phone " +
                                                                                            "FROM customers " +
                                                                                            "WHERE id = 6"
            );
            System.out.println("Retrieved row from customers table");

            // declare an oracle.sql.ROWID object to store the ROWID, and
            // an oracle.sql.CHAR object to store the phone column
            oracle.sql.ROWID rowid;
            oracle.sql.CHAR phone = new oracle.sql.CHAR("", myCharSet);

            // display the column values for row using the
            // get methods to read the values
            while (customerResultSet.next()) {
                rowid = customerResultSet.getROWID("ROWID");
                id = customerResultSet.getNUMBER("id");
                firstName = customerResultSet.getCHAR("first_name");
                lastName = customerResultSet.getCHAR("last_name");
                dob = customerResultSet.getDATE("dob");
                phone = customerResultSet.getCHAR("phone");

                System.out.println("rowid = " + rowid.stringValue());
                System.out.println("id = " + id.stringValue());
                System.out.println("firstName = " + firstName);
                System.out.println("lastName = " + lastName);
                System.out.println("dob = " + dob.stringValue());
                System.out.println("phone = " + phone);
            } // end of while loop

            // close the OracleResultSet object using the close() method
            customerResultSet.close();

            // rollback the changes made to the database
            myConnection.rollback();

            // close the other JDBC objects
            myPrepStatement.close();
            myConnection.close();

        } catch (SQLException e) {

            System.out.println("Error code = " + e.getErrorCode());
            System.out.println("Error message = " + e.getMessage());
            System.out.println("SQL state = " + e.getSQLState());
            e.printStackTrace();

        }
    } // end of main()
}