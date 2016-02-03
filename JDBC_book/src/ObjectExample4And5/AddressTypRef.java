package ObjectExample4And5;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class AddressTypRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "OBJECT_USER.ADDRESS_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final AddressTypRef _AddressTypRefFactory = new AddressTypRef();

    public static ORADataFactory getORADataFactory() {
        return _AddressTypRefFactory;
    }

    /* constructor */
    public AddressTypRef() {
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        AddressTypRef r = new AddressTypRef();
        r._ref = (REF) d;
        return r;
    }

    public AddressTyp getValue() throws SQLException {
        return (AddressTyp) AddressTyp.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(AddressTyp c) throws SQLException {
        _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
    }
}
