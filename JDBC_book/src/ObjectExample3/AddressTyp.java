package ObjectExample3;

import java.sql.SQLException;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;

import java.sql.SQLData;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class AddressTyp implements SQLData {
    public static final String _SQL_NAME = "OBJECT_USER.ADDRESS_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    private String m_street;
    private String m_city;
    private String m_state;
    private String m_zip;

    /* constructor */
    public AddressTyp() {
    }

    public void readSQL(SQLInput stream, String type)
            throws SQLException {
        setStreet(stream.readString());
        setCity(stream.readString());
        setState(stream.readString());
        setZip(stream.readString());
    }

    public void writeSQL(SQLOutput stream)
            throws SQLException {
        stream.writeString(getStreet());
        stream.writeString(getCity());
        stream.writeString(getState());
        stream.writeString(getZip());
    }

    public String getSQLTypeName() throws SQLException {
        return _SQL_NAME;
    }

    /* accessor methods */
    public String getStreet() {
        return m_street;
    }

    public void setStreet(String street) {
        m_street = street;
    }


    public String getCity() {
        return m_city;
    }

    public void setCity(String city) {
        m_city = city;
    }


    public String getState() {
        return m_state;
    }

    public void setState(String state) {
        m_state = state;
    }


    public String getZip() {
        return m_zip;
    }

    public void setZip(String zip) {
        m_zip = zip;
    }

}
