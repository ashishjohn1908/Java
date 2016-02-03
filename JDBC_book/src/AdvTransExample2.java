/*
  AdvTransExample2.java shows how to use a
  distributed transaction
*/

// import the required packages

import java.sql.*;
import javax.sql.*;
import javax.transaction.xa.*;

import oracle.jdbc.xa.OracleXid;
import oracle.jdbc.xa.client.*;

public class AdvTransExample2 {

    public static void main(String args[]) throws SQLException, XAException {

        // register the Oracle JDBC drivers
        DriverManager.registerDriver( new oracle.jdbc.OracleDriver() );

        // step 1: create two OracleXADataSource objects and set their attributes
        // (one represents the local data source, the other the remote
        // data source, these data sources would typically access different
        // databases, but in this example they access the same database
        // and schema)
        OracleXADataSource myOXADSLocal = new OracleXADataSource();
        myOXADSLocal.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        myOXADSLocal.setUser("store_user");
        myOXADSLocal.setPassword("store_password");

        OracleXADataSource myOXADSRemote = new OracleXADataSource();
        myOXADSRemote.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        myOXADSRemote.setUser("store_user");
        myOXADSRemote.setPassword("store_password");

        // step 2: create two XAConnection objects, these represent
        // physical connections to the database
        XAConnection myXACLocal = myOXADSLocal.getXAConnection();
        XAConnection myXACRemote = myOXADSRemote.getXAConnection();

        // step 3: create two Connection objects
        Connection myConnLocal = myXACLocal.getConnection();
        myConnLocal.setAutoCommit(false);
        Connection myConnRemote = myXACRemote.getConnection();
        myConnRemote.setAutoCommit(false);

        // step 4: create two XAResource objects, these manage
        // the two transaction branches
        XAResource myXARLocal = myXACLocal.getXAResource();
        XAResource myXARRemote = myXACRemote.getXAResource();

        // step 5: create the global transaction id to identify
        // the distributed transaction
        byte[] gid = new byte[64];
        gid[0] = (byte) 1;

        // step 6: create two branch qualifier ids, these identify
        // the different transaction branches
        byte[] bqidLocal = new byte[64];
        bqidLocal[0] = (byte) 1;
        byte[] bqidRemote = new byte[64];
        bqidRemote[0] = (byte) 2;

        // step 7: create two Xids, these are the transaction
        // branch ids
        Xid myXidLocal = new OracleXid(0x1234, gid, bqidLocal);
        Xid myXidRemote = new OracleXid(0x1234, gid, bqidRemote);

        // step 8: start the two transaction branches that make up the
        // distributed transaction by calling the XARResource object's
        // start() method
        myXARLocal.start(myXidLocal, XAResource.TMNOFLAGS);
        myXARRemote.start(myXidRemote, XAResource.TMNOFLAGS);

        // step 9: perform the SQL statements for each transaction branch
        Statement myStatement = myConnLocal.createStatement();

       myStatement.executeUpdate( "INSERT INTO products (id, type_id, name, description, price) " +
                                   "VALUES (STORE_USER.SEQ_PRODUCTS.NEXTVAL, 1, 'JDBC Programming', 'Java programming', 49.99)");

        myStatement = myConnRemote.createStatement();


        myStatement.executeUpdate( "INSERT INTO purchases (product_id, purchased_by, quantity) " +
                                   " VALUES (F_CurrValSeq, 1, 1)");

        // step 10: end the two transaction branches that make up the
        // distributed transaction by calling the XARResource object's
        // end() method
        myXARRemote.end(myXidRemote, XAResource.TMSUCCESS);
        myXARLocal.end(myXidLocal, XAResource.TMSUCCESS);

        // step 11: prepare the two transaction branches (phase 1 of the
        // two-phase commit) by calling the XARResouce object's
        /// prepare() method
        int resultLocal = myXARLocal.prepare(myXidLocal);
        int resultRemote = myXARRemote.prepare(myXidRemote);

        // step 12: check the results of the prepare phase
        boolean doCommit = true;
        if (!((resultLocal == XAResource.XA_OK) || (resultLocal == XAResource.XA_RDONLY))) {
            doCommit = false;
        }
        if (!((resultRemote == XAResource.XA_OK) || (resultRemote == XAResource.XA_RDONLY))) {
            doCommit = false;
        }

        // step 13: either do the commit or rollback (phase 2 of
        // the two-phase commit)
        if (resultLocal == XAResource.XA_OK) {
            if (doCommit) {
                System.out.println("Performing local commit");
                myXARLocal.commit(myXidLocal, false);
            } else {
                System.out.println("Performing local rollback");
                myXARLocal.rollback(myXidLocal);
            }
        }
        if (resultRemote == XAResource.XA_OK) {
            if (doCommit) {
                System.out.println("Performing remote commit");
                myXARRemote.commit(myXidRemote, false);
            } else {
                System.out.println("Performing remote rollback");
                myXARRemote.rollback(myXidRemote);
            }
        }

        // step 14: close the JDBC objects
        myConnLocal.close();
        myConnRemote.close();
        myXACLocal.close();
        myXACRemote.close();
        myStatement.close();

    } // end of main()

}