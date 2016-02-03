package CollectionExample3And4;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AddressTyp implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "COLLECTION_USER.ADDRESS_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    private static int[] _sqlType = {12, 12, 1, 12};
    private static ORADataFactory[] _factory = new ORADataFactory[4];
    private static final AddressTyp _AddressTypFactory = new AddressTyp();

    public static ORADataFactory getORADataFactory() {
        return _AddressTypFactory;
    }

    /* constructor */
    protected AddressTyp(boolean init) {
        if (init) _struct = new MutableStruct(new Object[4], _sqlType, _factory);
    }

    public AddressTyp() {
        this(true);
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _struct.toDatum(c, _SQL_NAME);
    }

    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    protected ORAData create(AddressTyp o, Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        if (o == null) o = new AddressTyp(false);
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    public String getStreet() throws SQLException {
        return (String) _struct.getAttribute(0);
    }

    public void setStreet(String street) throws SQLException {
        _struct.setAttribute(0, street);
    }


    public String getCity() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setCity(String city) throws SQLException {
        _struct.setAttribute(1, city);
    }


    public String getState() throws SQLException {
        return (String) _struct.getAttribute(2);
    }

    public void setState(String state) throws SQLException {
        _struct.setAttribute(2, state);
    }


    public String getZip() throws SQLException {
        return (String) _struct.getAttribute(3);
    }

    public void setZip(String zip) throws SQLException {
        _struct.setAttribute(3, zip);
    }

}
