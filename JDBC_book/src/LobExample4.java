/*
  LobExample4.java illustrates how to
  read content from CLOB and BLOB columns
  using streams.
*/

// import the JDBC packages

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;
import oracle.sql.CLOB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

// import the Oracle JDBC extension packages

public class LobExample4 {

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
        String targetDirectory = "D:\\PDF\\Java\\JDBC_book\\sample_files\\retrieved\\";
        readCLOB(myStatement, "textContent.txt", sourceDirectory, targetDirectory);
        readBLOB(myStatement, "binaryContent.doc", sourceDirectory, targetDirectory);

        // close the JDBC objects
        myStatement.close();
        myConnection.close();

    } // end of main()


    private static void readCLOB(Statement myStatement,String fileName,String sourceDirectory,String targetDirectory)
                                                                                        throws SQLException, IOException {

        // step 1: retrieve the row containing the LOB locator
        ResultSet clobResultSet = myStatement.executeQuery("SELECT clob_column FROM clob_content " +
                                                            "WHERE file_name = '" + sourceDirectory + fileName + "'"
                                                          );
        clobResultSet.next();

        // step 2: create a LOB object and read the LOB locator
        CLOB myClob = ((OracleResultSet) clobResultSet).getCLOB("clob_column");

        // step 3: create an input stream object and call the appropriate
        // LOB object input stream function
        InputStream myInputStream = myClob.getAsciiStream();

        // step 4: save the contents of the LOB to a new file,
        // reading the LOB contents from the input stream object
        String saveFileName = targetDirectory + "readCLOB2" + fileName;
        saveFile(myInputStream, saveFileName);

        // step 5: close the input stream object
        myInputStream.close();

        System.out.println( "Read CLOB and saved file " + saveFileName );

    } // end of readCLOB()


    private static void readBLOB(Statement myStatement,String fileName,String sourceDirectory,String targetDirectory)
                                                                                        throws SQLException, IOException {

        // step 1: retrieve the row containing the LOB locator
        ResultSet blobResultSet = myStatement.executeQuery( "SELECT blob_column FROM blob_content " +
                                                             "WHERE file_name = '" + sourceDirectory + fileName + "'"
                                                           );
        blobResultSet.next();

        // step 2: create a LOB object and read the LOB locator
        BLOB myBlob = ((OracleResultSet) blobResultSet).getBLOB("blob_column");

        // step 3: create an input stream object and call the appropriate
        // LOB object input stream function
        InputStream myInputStream = myBlob.getBinaryStream();

        // step 4: save the contents of the LOB to a new file,
        // reading the LOB contents from the input stream object
        String saveFileName = targetDirectory + "readBLOB2" + fileName;
        saveFile(myInputStream, saveFileName);

        // step 5: close the input stream object
        myInputStream.close();

        System.out.println("Read BLOB and saved file " + saveFileName);

    } // end of readBLOB()


    private static void saveFile(InputStream myInputStream,String fileName) throws IOException {

        // create a file object
        File myFile = new File(fileName);

        // create a file output stream
        FileOutputStream myFileOutputStream = new FileOutputStream(myFile);

        // read the contents from the input stream until
        // the end has been reached (the read() method
        // returns -1 at the end)
        byte[] byteBuffer = new byte[8132];
        int bytesRead;
        while ((bytesRead = myInputStream.read(byteBuffer)) != -1) {

            // write the input to the file
            myFileOutputStream.write(byteBuffer);

        } // end of while

        // close the file output stream
        myFileOutputStream.close();

    } // end of saveFile()

}