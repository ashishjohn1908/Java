/*
  LongExample1.java illustrates how to
  write content to LONG and LONG RAW columns
  using streams.
*/

// import the JDBC packages

import java.sql.*;
import java.io.*;

// import the Oracle JDBC extension packages
import oracle.sql.*;
import oracle.jdbc.*;

public class LongExample1 {

    public static void main(String[] args)
            throws SQLException, IOException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver(
                new oracle.jdbc.OracleDriver()
        );

        // create a Connection object, and connect to the database
        // as lob_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1523:ORCL",
                "lob_user",
                "lob_password"
        );

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        String sourceDirectory = "C:\\sample_files\\";
        writeLONG(myConnection, sourceDirectory, "textContent.txt");
        writeLONGRAW(myConnection, sourceDirectory, "binaryContent.doc");

        // close the JDBC connection object
        myConnection.close();

    } // end of main()


    private static void writeLONG(
            Connection myConnection,
            String sourceDirectory,
            String fileName
    ) throws SQLException, IOException {

        // step 1: create a file object
        File myFile = new File(sourceDirectory + fileName);

        // step 2: get the file length
        int fileLength = (int) myFile.length();

        // step 3: create an input stream object to read
        // the file contents
        InputStream myInputStream = new FileInputStream(myFile);

        // step 4: create a prepared statetment object
        // to add a row to the long_content table
        PreparedStatement myPrepStatement = myConnection.prepareStatement(
                "INSERT INTO long_content(file_name, long_column) " +
                        "VALUES (?, ?)"
        );

        // step 5: bind the file name and input stream to the
        // prepared statement object
        myPrepStatement.setString(1, fileName);
        myPrepStatement.setAsciiStream(2, myInputStream, fileLength);

        // step 6: run the SQL statement contained in the
        // prepared statement object
        myPrepStatement.execute();

        // step 7: perform a commit
        myConnection.commit();

        // step 8: close the input stream and prepared statement objects
        myInputStream.close();
        myPrepStatement.close();

        System.out.println("Wrote content from file " +
                fileName + " to LONG");

    } // end of writeLONG()


    private static void writeLONGRAW(
            Connection myConnection,
            String sourceDirectory,
            String fileName
    ) throws SQLException, IOException {

        // step 1: create a file object
        File myFile = new File(sourceDirectory + fileName);

        // step 2: get the file length
        int fileLength = (int) myFile.length();

        // step 3: create an input stream object to read
        // the file contents
        InputStream myInputStream = new FileInputStream(myFile);

        // step 4: create a prepared statetment object
        // to add a row to the long_raw_content table
        PreparedStatement myPrepStatement = myConnection.prepareStatement(
                "INSERT INTO long_raw_content(file_name, long_raw_column) " +
                        "VALUES (?, ?)"
        );

        // step 5: bind the file name and input stream to the
        // prepared statement object
        myPrepStatement.setString(1, fileName);
        myPrepStatement.setBinaryStream(2, myInputStream, fileLength);

        // step 6: run the SQL statement contained in the
        // prepared statement object
        myPrepStatement.execute();

        // step 7: perform a commit
        myConnection.commit();

        // step 8: close the input stream and prepared statement objects
        myInputStream.close();
        myPrepStatement.close();

        System.out.println("Wrote content from file " +
                fileName + " to LONG RAW");

    } // end of writeLONGRAW()

}