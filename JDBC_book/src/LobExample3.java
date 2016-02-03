/*
  LobExample3.java illustrates how to
  write content to CLOB and BLOB columns
  using streams.
*/

// import the JDBC packages

import java.sql.*;
import java.io.*;

// import the Oracle JDBC extension packages
import oracle.sql.*;
import oracle.jdbc.*;

public class LobExample3 {

    public static void main(String[] args)  throws SQLException, IOException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver( new oracle.jdbc.OracleDriver() );

        // create a Connection object, and connect to the database
        // as lob_user using the Oracle JDBC Thin driver
        Connection myConnection = DriverManager.getConnection(  "jdbc:oracle:thin:@localhost:1523:ORCL",
                                                                "lob_user",
                                                                "lob_password"
                                                             );

        // disable auto-commit mode
        myConnection.setAutoCommit(false);

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        String sourceDirectory = "D:\\PDF\\Java\\JDBC_book\\sample_files\\";
        writeCLOB(myStatement, sourceDirectory + "textContent.txt");
        writeBLOB(myStatement, sourceDirectory + "binaryContent.doc");

        // close the JDBC objects
        myStatement.close();
        myConnection.close();

    } // end of main()


    private static void writeCLOB( Statement myStatement, String fileName ) throws SQLException, IOException {

        // step 1: initialize the LOB column to set the LOB locator
        myStatement.executeUpdate(  "INSERT INTO clob_content(file_name, clob_column) " +
                                     "VALUES ('" + fileName + "', EMPTY_CLOB())"
                                 );

        // step 2: retrieve the row containing the LOB locator
        ResultSet clobResultSet = myStatement.executeQuery( "SELECT clob_column " +
                                                            "FROM clob_content " +
                                                            "WHERE file_name = '" + fileName + "' " +
                                                            "FOR UPDATE"
                                                          );
        clobResultSet.next();

        // step 3: create a LOB object and read the LOB locator
        CLOB myClob = ((OracleResultSet) clobResultSet).getCLOB("clob_column");

        // step 4: get the buffer size of the LOB from the LOB object
        int bufferSize = myClob.getBufferSize();

        // step 5: create a buffer to hold a block of data from the file
        byte[] byteBuffer = new byte[bufferSize];

        // step 6: create a file object
        File myFile = new File(fileName);

        // step 7: create a file input stream object to read
        // the file contents
        FileInputStream myFileInputStream = new FileInputStream(myFile);

        // step 8: create an input stream object and call the appropriate
        // LOB object output stream function
        OutputStream myOutputStream = myClob.setAsciiStream(1L);

        // step 9: while the end of the file has not been reached,
        // read a block from the file into the buffer, and write the
        // buffer contents to the LOB object via the output stream
        int bytesRead;

        while ((bytesRead = myFileInputStream.read(byteBuffer)) != -1) {

            // write the buffer contents to the output stream
            // using the write() method
            myOutputStream.write(byteBuffer);

        } // end of while

        // step 10: close the stream objects
        myFileInputStream.close();
        myOutputStream.close();

        System.out.println("Wrote content from file " + fileName + " to CLOB");

    } // end of writeCLOB()


    private static void writeBLOB( Statement myStatement, String fileName ) throws SQLException, IOException {

        // step 1: initialize the LOB column to set the LOB locator
        myStatement.executeUpdate( "INSERT INTO blob_content(file_name, blob_column) " +
                                    "VALUES ('" + fileName + "', EMPTY_BLOB())"
                                 );

        // step 2: retrieve the row containing the LOB locator
        ResultSet blobResultSet = myStatement.executeQuery( "SELECT blob_column " +
                                                            "FROM blob_content " +
                                                            "WHERE file_name = '" + fileName + "' " +
                                                            "FOR UPDATE"
                                                          );
        blobResultSet.next();

        // step 3: create a LOB object and read the LOB locator
        BLOB myBlob = ((OracleResultSet) blobResultSet).getBLOB("blob_column");

        // step 4: get the buffer size of the LOB from the LOB object
        int bufferSize = myBlob.getBufferSize();

        // step 5: create a buffer to hold a block of data from the file
        byte[] byteBuffer = new byte[bufferSize];

        // step 6: create a file object
        File myFile = new File(fileName);

        // step 7: create a file input stream object to read
        // the file contents
        FileInputStream myFileInputStream = new FileInputStream(myFile);

        // step 8: create an input stream object and call the appropriate
        // LOB object output stream function
        OutputStream myOutputStream = myBlob.setBinaryStream(1L);

        // step 9: while the end of the file has not been reached,
        // read a block from the file into the buffer, and write the
        // buffer contents to the LOB object via the output stream
        int bytesRead;

        while ((bytesRead = myFileInputStream.read(byteBuffer)) != -1) {

            // write the buffer contents to the output stream
            // using the write() method
            myOutputStream.write(byteBuffer);

        } // end of while

        // step 10: close the stream objects
        myFileInputStream.close();
        myOutputStream.close();

        System.out.println("Wrote content from file " + fileName + " to BLOB");

    } // end of writeBLOB()

}