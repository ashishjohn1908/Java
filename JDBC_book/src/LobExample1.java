/*
  LobExample1.java illustrates how to
  use the put methods to write content to
  CLOB and BLOB columns in the database.
  It also shows how to add pointers to
  external files using BFILE columns.
*/

// import the JDBC packages

import java.sql.*;
import java.io.*;

// import the Oracle JDBC extension packages
import oracle.sql.*;
import oracle.jdbc.*;

public class LobExample1 {

    public static void main(String[] args) throws SQLException {
        Connection myConnection = null;
        Statement myStatement = null;


        try {
            // register the Oracle JDBC drivers
            DriverManager.registerDriver( new OracleDriver() );


            // create a Connection object, and connect to the database
            // as lob_user using the Oracle JDBC Thin driver
            myConnection = DriverManager.getConnection(  "jdbc:oracle:thin:@localhost:1521:ORCL",
                                                         "lob_user",
                                                         "lob_password"
                                                      );

            // disable auto-commit mode
            myConnection.setAutoCommit(false);

            // create a statement object
            myStatement = myConnection.createStatement();

            String sourceDirectory = "D:\\PDF\\Java\\JDBC_book\\sample_files\\";
            writeCLOB(myStatement, sourceDirectory + "textContent.txt");
            writeBLOB(myStatement, sourceDirectory + "binaryContent.doc");
            addBFILE(myStatement, "SAMPLE_FILES_DIR", "textContent.txt");
            addBFILE(myStatement, "SAMPLE_FILES_DIR", "binaryContent.doc");
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }finally {
            // close the JDBC objects
            myStatement.close();
            myConnection.close();
        }


    } // end of main()


    private static void writeCLOB( Statement myStatement, String fileName ) throws SQLException, IOException {

        // step 1: initialize the LOB column to set the LOB locator
        myStatement.executeUpdate( "INSERT INTO clob_content(file_name, clob_column) " +
                                    "VALUES ('" + fileName + "', EMPTY_CLOB())"
                                 );

        // step 2: retrieve the row containing the LOB locator
        ResultSet clobResultSet = myStatement.executeQuery( "SELECT clob_column FROM clob_content " +
                                                            "WHERE file_name = '" + fileName + "' " +
                                                            "FOR UPDATE"
                                                          );
        clobResultSet.next();

        // step 3: create a LOB object and read the LOB locator
        CLOB myClob =  ((OracleResultSet) clobResultSet).getCLOB("clob_column");

        // step 4: get the chunk size of the LOB from the LOB object
        int chunkSize = myClob.getChunkSize();

        // step 5: create a buffer to hold a block of data from the file
        char[] textBuffer = new char[chunkSize];

        // step 6: create a file object
        File myFile = new File(fileName);

        // step 7: create input stream objects to read the file contents
        FileInputStream myFileInputStream = new FileInputStream(myFile);
        InputStreamReader myReader =  new InputStreamReader(myFileInputStream);
        BufferedReader myBufferedReader = new BufferedReader(myReader);

        // step 8: read the file contents and write it to the LOB
        long position = 1;
        int charsRead;

        while ((charsRead = myBufferedReader.read(textBuffer)) != -1) {

            // write the buffer contents to myClob using the putChars() method
            myClob.putChars(position, textBuffer);

            // increment the end position
            position += charsRead;

        } // end of while

        // step 9: perform a commit
        myStatement.execute("COMMIT");

        // step 10: close the objects used to read the file
        myBufferedReader.close();
        myReader.close();
        myFileInputStream.close();

        System.out.println("Wrote content from file " + fileName + " to CLOB");

    } // end of writeCLOB()


    private static void writeBLOB( Statement myStatement, String fileName ) throws SQLException, IOException {

        // step 1: initialize the LOB column to set the LOB locator
        myStatement.executeUpdate( "INSERT INTO blob_content(file_name, blob_column) " +
                                   "VALUES ('" + fileName + "', EMPTY_BLOB())"
                                 );

        // step 2: retrieve the row containing the LOB locator
        ResultSet blobResultSet = myStatement.executeQuery( "SELECT blob_column FROM blob_content " +
                                                            "WHERE file_name = '" + fileName + "' " +
                                                            "FOR UPDATE"
                                                          );
        blobResultSet.next();

        // step 3: create a LOB object and read the LOB locator
        BLOB myBlob =  ((OracleResultSet) blobResultSet).getBLOB("blob_column");

        // step 4: get the chunk size of the LOB from the LOB object
        int chunkSize = myBlob.getChunkSize();

        // step 5: create a buffer to hold a block of data from the file
        byte[] byteBuffer = new byte[chunkSize];

        // step 6: create a file object to open the file
        File myFile = new File(fileName);

        // step 7: create an input stream object to read the file contents
        FileInputStream myFileInputStream = new FileInputStream(myFile);

        // step 8: read the file contents and write it to the LOB
        long position = 1;
        int bytesRead;

        while ((bytesRead = myFileInputStream.read(byteBuffer)) != -1) {

            // write the buffer contents to myBlob using the putBytes() method
            myBlob.setBytes(position, byteBuffer);

            // increment the end position
            position += bytesRead;

        } // end of while

        // step 9: perform a COMMIT
        myStatement.execute("COMMIT");

        // step 10: close the objects used to read the file
        myFileInputStream.close();

        System.out.println("Wrote content from file " + fileName + " to BLOB");

    } // end of writeBLOB()


    private static void addBFILE( Statement myStatement, String directory, String fileName ) throws SQLException {

        myStatement.executeUpdate( "INSERT INTO bfile_content(file_name, bfile_column) " +
                                    "VALUES ('" + fileName + "', " + "BFILENAME('" + directory + "', '" + fileName + "'))"
                                 );
        myStatement.execute("COMMIT");

        System.out.println("Added pointer to file " + fileName + " to BFILE in database directory " + directory);

    } // end of addBFILE()

}