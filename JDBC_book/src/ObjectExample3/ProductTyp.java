package ObjectExample3;

import oracle.jdbc.OracleTypes;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class ProductTyp implements SQLData {
    public static final String _SQL_NAME = "OBJECT_USER.PRODUCT_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    private java.math.BigDecimal m_id;
    private String m_name;
    private String m_description;
    private java.math.BigDecimal m_price;
    private java.math.BigDecimal m_daysValid;

    /* constructor */
    public ProductTyp() {
    }

    public void readSQL(SQLInput stream, String type) throws SQLException {
        setId(stream.readBigDecimal());
        setName(stream.readString());
        setDescription(stream.readString());
        setPrice(stream.readBigDecimal());
        setDaysValid(stream.readBigDecimal());
    }

    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeBigDecimal(getId());
        stream.writeString(getName());
        stream.writeString(getDescription());
        stream.writeBigDecimal(getPrice());
        stream.writeBigDecimal(getDaysValid());
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


    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        m_name = name;
    }


    public String getDescription() {
        return m_description;
    }

    public void setDescription(String description) {
        m_description = description;
    }


    public java.math.BigDecimal getPrice() {
        return m_price;
    }

    public void setPrice(java.math.BigDecimal price) {
        m_price = price;
    }


    public java.math.BigDecimal getDaysValid() {
        return m_daysValid;
    }

    public void setDaysValid(java.math.BigDecimal daysValid) {
        m_daysValid = daysValid;
    }

}
