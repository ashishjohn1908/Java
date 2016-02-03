package ObjectExample4And5;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class PersonTypRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "OBJECT_USER.PERSON_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final PersonTypRef _PersonTypRefFactory = new PersonTypRef();

    public static ORADataFactory getORADataFactory() {
        return _PersonTypRefFactory;
    }

    /* constructor */
    public PersonTypRef() {
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        PersonTypRef r = new PersonTypRef();
        r._ref = (REF) d;
        return r;
    }

    public PersonTyp2 getValue() throws SQLException {
        return (PersonTyp2) PersonTyp2.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(PersonTyp2 c) throws SQLException {
        _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
    }
}
