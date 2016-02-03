package ObjectExample3;

import java.sql.SQLException;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;

import java.sql.SQLData;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;

public class PersonTyp implements SQLData {
    public static final String _SQL_NAME = "OBJECT_USER.PERSON_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    private java.math.BigDecimal m_id;
    private String m_firstName;
    private String m_lastName;
    private java.sql.Timestamp m_dob;
    private String m_phone;
    private AddressTyp m_address;

    /* constructor */
    public PersonTyp() {
    }

    public void readSQL(SQLInput stream, String type)
            throws SQLException {
        setId(stream.readBigDecimal());
        setFirstName(stream.readString());
        setLastName(stream.readString());
        setDob(stream.readTimestamp());
        setPhone(stream.readString());
        setAddress((AddressTyp) stream.readObject());
    }

    public void writeSQL(SQLOutput stream)
            throws SQLException {
        stream.writeBigDecimal(getId());
        stream.writeString(getFirstName());
        stream.writeString(getLastName());
        stream.writeTimestamp(getDob());
        stream.writeString(getPhone());
        stream.writeObject(getAddress());
    }

    public String getSQLTypeName() throws SQLException {
        return _SQL_NAME;
    }

    /* accessor methods */
    public java.math.BigDecimal getId() {
        return m_id;
    }

    public void setId(java.math.BigDecimal id) {
        m_id = id;
    }


    public String getFirstName() {
        return m_firstName;
    }

    public void setFirstName(String firstName) {
        m_firstName = firstName;
    }


    public String getLastName() {
        return m_lastName;
    }

    public void setLastName(String lastName) {
        m_lastName = lastName;
    }


    public java.sql.Timestamp getDob() {
        return m_dob;
    }

    public void setDob(java.sql.Timestamp dob) {
        m_dob = dob;
    }


    public String getPhone() {
        return m_phone;
    }

    public void setPhone(String phone) {
        m_phone = phone;
    }


    public AddressTyp getAddress() {
        return m_address;
    }

    public void setAddress(AddressTyp address) {
        m_address = address;
    }

}
