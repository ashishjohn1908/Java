package CollectionExample3And4;

import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.jpub.runtime.MutableArray;
import oracle.jdbc.pool.OracleDataSource;

public class NestedTableAddressTyp implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "COLLECTION_USER.NESTED_TABLE_ADDRESS_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.ARRAY;

    MutableArray _array;

    private static final NestedTableAddressTyp _NestedTableAddressTypFactory = new NestedTableAddressTyp();

    public static ORADataFactory getORADataFactory() {
        return _NestedTableAddressTypFactory;
    }

    /* constructors */
    public NestedTableAddressTyp() {
        this((AddressTyp[]) null);
    }

    public NestedTableAddressTyp(AddressTyp[] a) {
        _array = new MutableArray(2002, a, AddressTyp.getORADataFactory());
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        return _array.toDatum(c, _SQL_NAME);
    }

    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        if (d == null) return null;
        NestedTableAddressTyp a = new NestedTableAddressTyp();
        a._array = new MutableArray(2002, (ARRAY) d, AddressTyp.getORADataFactory());
        return a;
    }

    public int length() throws SQLException {
        return _array.length();
    }

    public int getBaseType() throws SQLException {
        return _array.getBaseType();
    }

    public String getBaseTypeName() throws SQLException {
        return _array.getBaseTypeName();
    }

    public ArrayDescriptor getDescriptor() throws SQLException {
        return _array.getDescriptor();
    }

    /* array accessor methods */
    public AddressTyp[] getArray() throws SQLException {
        return (AddressTyp[]) _array.getObjectArray(new AddressTyp[_array.length()]);
    }

    public void setArray(AddressTyp[] a) throws SQLException {
        _array.setObjectArray(a);
    }

    public AddressTyp[] getArray(long index, int count) throws SQLException {
        return (AddressTyp[]) _array.getObjectArray(index, new AddressTyp[_array.sliceLength(index, count)]);
    }

    public void setArray(AddressTyp[] a, long index) throws SQLException {
        _array.setObjectArray(a, index);
    }

    public AddressTyp getElement(long index) throws SQLException {
        return (AddressTyp) _array.getObjectElement(index);
    }

    public void setElement(AddressTyp a, long index) throws SQLException {
        _array.setObjectElement(a, index);
    }

}
