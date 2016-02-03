/*
  LobExample2.java illustrates how to
  use the get methods to read from CLOB and BLOB columns.
  It also shows how to retrieve pointers to
  external files using BFILE columns, and copy
  the contents of those files to new files.
*/

// import the JDBC packages

import java.sql.*;
import java.io.*;

// import the Oracle JDBC extension packages
import oracle.sql.*;
import oracle.jdbc.*;

public class LobExample2 {

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

        // create a Statement object
        Statement myStatement = myConnection.createStatement();

        String sourceDirectory = "C:\\sample_files\\";
        String targetDirectory = "C:\\sample_files\\retrieved\\";
        readCLOB(
                myStatement, "textContent.txt", sourceDirectory, targetDirectory
        );
        readBLOB(
                myStatement, "binaryContent.doc", sourceDirectory, targetDirectory
        );
        retrieveBFILE(myStatement, "textContent.txt", targetDirectory);
        retrieveBFILE(myStatement, "binaryContent.doc", targetDirectory);

        // close the JDBC objects
        myStatement.close();
        myConnection.close();

    } // end of main()


    private static void readCLOB(
            Statement myStatement,
            String fileName,
            String sourceDirectory,
            String targetDirectory
    ) throws SQLException, IOException {

        // step 1: retrieve the row containing the LOB locator
        ResultSet clobResultSet = myStatement.executeQuery(
                "SELECT clob_column " +
                        "FROM clob_content " +
                        "WHERE file_name = '" + sourceDirectory + fileName + "'"
        );
        clobResultSet.next();

        // step 2: create a LOB object and read the LOB locator
        CLOB myClob =
                ((OracleResultSet) clobResultSet).getCLOB("clob_column");

        // step 3: get the chunk size of the LOB from the LOB object
        int chunkSize = myClob.getChunkSize();

        // step 4: create a buffer to hold a chunk of data retrieved from
        // the LOB object
        char[] textBuffer = new char[chunkSize];

        // step 5: create a file object
        String saveFile = targetDirectory + "readCLOB" + fileName;
        File myFile = new File(saveFile);

        // step 6: create output stream objects to write the LOB contents
        // to the new file
        FileOutputStream myFileOutputStream =
                new FileOutputStream(myFile);
        OutputStreamWriter myWriter =
                new OutputStreamWriter(myFileOutputStream);
        BufferedWriter myBufferedWriter = new BufferedWriter(myWriter);

        // step 7: get the length of the LOB contents from the LOB object
        long clobLength = myClob.length();

        // step 8: while the end of the LOB contents has not been reached,
        // read a chunk of data from the LOB into the buffer,
        // and write the buffer contents to the file
        for (
                long position = 1;
                position <= clobLength;
                position += chunkSize
                ) {

            // read a chunk of data from myClob using the getChars() method
            // and store it in the buffer
            int charsRead =
                    myClob.getChars(position, chunkSize, textBuffer);

            // write the buffer contents to the file
            myBufferedWriter.write(textBuffer);

        } // end of for

        // step 9: close the stream objects
        myBufferedWriter.close();
        myWriter.close();
        myFileOutputStream.close();

        System.out.println(
                "Read CLOB and saved file " + saveFile
        );

    } // end of readCLOB()


    private static void readBLOB(
            Statement myStatement,
            String fileName,
            String sourceDirectory,
            String targetDirectory
    ) throws SQLException, IOException {

        // step 1: retrieve the row containing the LOB locator
        ResultSet blobResultSet = myStatement.executeQuery(
                "SELECT blob_column " +
                        "FROM blob_content " +
                        "WHERE file_name = '" + sourceDirectory + fileName + "'"
        );
        blobResultSet.next();

        // step 2: create a LOB object and read the LOB locator
        BLOB myBlob =
                ((OracleResultSet) blobResultSet).getBLOB("blob_column");

        // step 3: get the chunk size of the LOB from the LOB object
        int chunkSize = myBlob.getChunkSize();

        // step 4: create a buffer to hold a chunk of data retrieved from
        // the LOB object
        byte[] byteBuffer = new byte[chunkSize];

        // step 5: create a file object
        String saveFile = targetDirectory + "readBLOB" + fileName;
        File myFile = new File(saveFile);

        // step 6: create output stream objects to write the LOB contents
        // to the new file
        FileOutputStream myFileOutputStream =
                new FileOutputStream(myFile);

        // step 7: get the length of the LOB contents from the LOB object
        long blobLength = myBlob.length();

        // step 8: while the end of the LOB contents has not been reached,
        // read a chunk of data from the LOB into the buffer,
        // and write the buffer contents to the file
        for (
                long position = 1;
                position <= blobLength;
                position += chunkSize
                ) {

            // read a chunk of data from myBlob using the getBytes() method
            // and store it in the buffer
            int bytesRead =
                    myBlob.getBytes(position, chunkSize, byteBuffer);

            // write the buffer contents to the file
            myFileOutputStream.write(byteBuffer);

        } // end of for

        // step 9: close the stream objects
        myFileOutputStream.close();

        System.out.println(
                "Read BLOB and saved file " + saveFile
        );

    } // end of readBLOB()


    private static void retrieveBFILE(
            Statement myStatement,
            String fileName,
            String targetDirectory
    ) throws SQLException, IOException {

        // step 1: retrieve the row containing the BFILE locator
        ResultSet bfileResultSet = myStatement.executeQuery(
                "SELECT bfile_column " +
                        "FROM bfile_content " +
                        "WHERE file_name = '" + fileName + "'"
        );
        bfileResultSet.next();

        // step 2: create a BFILE object and read the locator
        BFILE myBfile =
                ((OracleResultSet) bfileResultSet).getBFILE("bfile_column");

        // step 3: get the file name from the BFILE object
        String bfileName = myBfile.getName();

        // step 4: check that the external file pointed to
        // by the BFILE object exists
        myBfile.fileExists();

        // step 5: open the external file via the BFILE object
        myBfile.openFile();

        // step 6: create an input stream object to read the external
        // file contents via the BFILE object
        InputStream myInputStream = myBfile.getBinaryStream();

        // step 7: save the file contents read from the
        // input stream to a new file
        String saveFileName = targetDirectory + "retrievedBFILE" +
                bfileName;
        saveFile(myInputStream, saveFileName);

        // step 8: close the input stream
        myInputStream.close();

        // step 9: close the external file via the BFILE object
        myBfile.closeFile();

        System.out.println(
                "Retrieved pointer from BFILE and saved file " + saveFileName
        );

    } // end of retrieveBFILE()


    private static void saveFile(
            InputStream myInputStream,
            String fileName
    ) throws IOException {

        // create a file object
        File myFile = new File(fileName);

        // create a file output stream
        FileOutputStream myFileOutputStream =
                new FileOutputStream(myFile);

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