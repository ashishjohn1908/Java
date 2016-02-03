/*
  LongExample2.java illustrates how to
  read content from LONG and LONG RAW
  columns using streams.
*/

// import the JDBC packages

import java.sql.*;
import java.io.*;

// import the Oracle JDBC extension packages
import oracle.sql.*;
import oracle.jdbc.*;

public class LongExample2 {

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
        readLONG(myStatement, targetDirectory);
        readLONGRAW(myStatement, targetDirectory);

        // close the JDBC objects
        myStatement.close();
        myConnection.close();

    } // end of main()


    private static void readLONG(
            Statement myStatement,
            String targetDirectory
    ) throws SQLException, IOException {

        // step 1: retrieve the rows into a result set object
        ResultSet longResultSet = myStatement.executeQuery(
                "SELECT file_name, long_column " +
                        "FROM long_content"
        );

        // step 2: create an input stream object
        InputStream myInputStream = null;

        // step 3: read the rows from the result set object
        while (longResultSet.next()) {

            String fileName = longResultSet.getString("file_name");
            String saveFileName =
                    targetDirectory + "readLONG" + fileName;

            // step 3a: get the ASCII stream from the
            // the result set, and store it in the
            // input stream object
            myInputStream = longResultSet.getAsciiStream("long_column");

            // step 3b: save the input stream object to a new file
            saveFile(myInputStream, saveFileName);

            System.out.println("Read LONG and saved file " +
                    saveFileName);

        } // end of while

        // step 4: close the input stream object
        myInputStream.close();

    } // end of readLONG()


    private static void readLONGRAW(
            Statement myStatement,
            String targetDirectory
    ) throws SQLException, IOException {

        // step 1: retrieve the rows into a result set object
        ResultSet longRawResultSet = myStatement.executeQuery(
                "SELECT file_name, long_raw_column " +
                        "FROM long_raw_content"
        );

        // step 2: create an input stream object
        InputStream myInputStream = null;

        // step 3: read the rows from the result set object
        while (longRawResultSet.next()) {

            String fileName = longRawResultSet.getString("file_name");
            String saveFileName =
                    targetDirectory + "readLONGRAW" + fileName;

            // step 3a: get the binary stream from the
            // the result set, and store it in the
            // input stream object
            myInputStream =
                    longRawResultSet.getBinaryStream("long_raw_column");

            // step 3b: save the input stream object to a new file
            saveFile(myInputStream, saveFileName);

            System.out.println(
                    "Read LONG RAW and saved file " +
                            saveFileName
            );

        } // end of while

        // step 4: close the input stream object
        myInputStream.close();

    } // end of readLONGRAW()


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