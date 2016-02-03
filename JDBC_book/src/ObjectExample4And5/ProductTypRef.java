package ObjectExample4And5;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.STRUCT;

public class ProductTypRef implements ORAData, ORADataFactory {
    public static final String _SQL_BASETYPE = "OBJECT_USER.PRODUCT_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.REF;

    REF _ref;

    private static final ProductTypRef _ProductTypRefFactory = new ProductTypRef();

    public static ORADataFactory getORADataFactory() {
        return _ProductTypRefFactory;
    }

    /* constructor */
    public ProductTypRef() {
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _ref;
    }

    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        ProductTypRef r = new ProductTypRef();
        r._ref = (REF) d;
        return r;
    }

    public ProductTyp getValue() throws SQLException {
        return (ProductTyp) ProductTyp.getORADataFactory().create(
                _ref.getSTRUCT(), OracleTypes.REF);
    }

    public void setValue(ProductTyp c) throws SQLException {
        _ref.setValue((STRUCT) c.toDatum(_ref.getJavaSqlConnection()));
    }
}
