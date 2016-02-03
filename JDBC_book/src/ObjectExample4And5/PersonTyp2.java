package ObjectExample4And5;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PersonTyp2 implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "OBJECT_USER.PERSON_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    protected MutableStruct _struct;

    private static int[] _sqlType = {2, 12, 12, 91, 12, 2002};
    private static ORADataFactory[] _factory = new ORADataFactory[6];

    static {
        _factory[5] = AddressTyp.getORADataFactory();
    }

    private static final PersonTyp2 _PersonTypFactory = new PersonTyp2();

    public static ORADataFactory getORADataFactory() {
        return _PersonTypFactory;
    }

    /* constructor */
    protected PersonTyp2(boolean init) {
        if (init) _struct = new MutableStruct(new Object[6], _sqlType, _factory);
    }

    public PersonTyp2() {
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

    protected ORAData create(PersonTyp2 o, Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        if (o == null) o = new PersonTyp2(false);
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        return o;
    }

    /* accessor methods */
    public java.math.BigDecimal getId() throws SQLException {
        return (java.math.BigDecimal) _struct.getAttribute(0);
    }

    public void setId(java.math.BigDecimal id) throws SQLException {
        _struct.setAttribute(0, id);
    }


    public String getFirstName() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setFirstName(String firstName) throws SQLException {
        _struct.setAttribute(1, firstName);
    }


    public String getLastName() throws SQLException {
        return (String) _struct.getAttribute(2);
    }

    public void setLastName(String lastName) throws SQLException {
        _struct.setAttribute(2, lastName);
    }


    public java.sql.Timestamp getDob() throws SQLException {
        return (java.sql.Timestamp) _struct.getAttribute(3);
    }

    public void setDob(java.sql.Timestamp dob) throws SQLException {
        _struct.setAttribute(3, dob);
    }


    public String getPhone() throws SQLException {
        return (String) _struct.getAttribute(4);
    }

    public void setPhone(String phone) throws SQLException {
        _struct.setAttribute(4, phone);
    }


    public AddressTyp getAddress() throws SQLException {
        return (AddressTyp) _struct.getAttribute(5);
    }

    public void setAddress(AddressTyp address) throws SQLException {
        _struct.setAttribute(5, address);
    }

}
