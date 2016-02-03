package ObjectExample4And5;
/*@lineinfo:filename=ProductTyp*//*@lineinfo:user-code*//*@lineinfo:1^1*/

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Connection;

import oracle.jdbc.OracleTypes;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.Datum;
import oracle.sql.STRUCT;
import oracle.jpub.runtime.MutableStruct;
import sqlj.runtime.ExecutionContext;
import sqlj.runtime.RuntimeContext;
import sqlj.runtime.error.RuntimeRefErrors;
import sqlj.runtime.profile.Loader;
import sqlj.runtime.profile.RTStatement;
import sqlj.runtime.ref.DefaultContext;
import sqlj.runtime.ConnectionContext;

import java.sql.Connection;
import java.sql.Timestamp;

public class ProductTyp implements ORAData, ORADataFactory {
    public static final String _SQL_NAME = "OBJECT_USER.PRODUCT_TYP";
    public static final int _SQL_TYPECODE = OracleTypes.STRUCT;

    /* connection management */
    protected DefaultContext __tx = null;
    protected Connection __onn = null;

    public void setConnectionContext(DefaultContext ctx) throws SQLException {
        release();
        __tx = ctx;
    }

    public DefaultContext getConnectionContext() throws SQLException {
        if (__tx == null) {
            __tx = (__onn == null) ? DefaultContext.getDefaultContext() : new DefaultContext(__onn);
        }
        return __tx;
    }

    public Connection getConnection() throws SQLException {
        return (__onn == null) ? ((__tx == null) ? null : __tx.getConnection()) : __onn;
    }

    public void release() throws SQLException {
        if (__tx != null && __onn != null)
            __tx.close(ConnectionContext.KEEP_CONNECTION);
        __onn = null;
        __tx = null;
    }

    protected MutableStruct _struct;

    private static int[] _sqlType = {2, 12, 12, 2, 2};
    private static ORADataFactory[] _factory = new ORADataFactory[5];
    private static final ProductTyp _ProductTypFactory = new ProductTyp(false);

    public static ORADataFactory getORADataFactory() {
        return _ProductTypFactory;
    }

    /* constructors */
    protected ProductTyp(boolean init) {
        if (init)
            _struct = new MutableStruct(new Object[5], _sqlType, _factory);
    }

    public ProductTyp() {
        this(true);
        __tx = DefaultContext.getDefaultContext();
    }

    public ProductTyp(DefaultContext c) throws SQLException {
        this(true);
        __tx = c;
    }

    public ProductTyp(Connection c) throws SQLException {
        this(true);
        __onn = c;
    }

    /* ORAData interface */
    public Datum toDatum(Connection c) throws SQLException {
        if (__tx != null && __onn != c)
            release();
        __onn = c;
        return _struct.toDatum(c, _SQL_NAME);
    }

    /* ORADataFactory interface */
    public ORAData create(Datum d, int sqlType) throws SQLException {
        return create(null, d, sqlType);
    }

    public void setFrom(ProductTyp o) throws SQLException {
        release();
        _struct = o._struct;
        __tx = o.__tx;
        __onn = o.__onn;
    }

    protected void setValueFrom(ProductTyp o) {
        _struct = o._struct;
    }

    protected ORAData create(ProductTyp o, Datum d, int sqlType) throws SQLException {
        if (d == null) {
            if (o != null) {
                o.release();
            }
            return null;
        }
        if (o == null)
            o = new ProductTyp(false);
        o._struct = new MutableStruct((STRUCT) d, _sqlType, _factory);
        o.__onn = ((STRUCT) d).getJavaSqlConnection();
        return o;
    }

    /* accessor methods */
    public BigDecimal getId() throws SQLException {
        return (BigDecimal) _struct.getAttribute(0);
    }

    public void setId(BigDecimal id) throws SQLException {
        _struct.setAttribute(0, id);
    }


    public String getName() throws SQLException {
        return (String) _struct.getAttribute(1);
    }

    public void setName(String name) throws SQLException {
        _struct.setAttribute(1, name);
    }


    public String getDescription() throws SQLException {
        return (String) _struct.getAttribute(2);
    }

    public void setDescription(String description) throws SQLException {
        _struct.setAttribute(2, description);
    }


    public BigDecimal getPrice() throws SQLException {
        return (BigDecimal) _struct.getAttribute(3);
    }

    public void setPrice(BigDecimal price) throws SQLException {
        _struct.setAttribute(3, price);
    }


    public BigDecimal getDaysValid() throws SQLException {
        return (BigDecimal) _struct.getAttribute(4);
    }

    public void setDaysValid(BigDecimal daysValid) throws SQLException {
        _struct.setAttribute(4, daysValid);
    }


    public Timestamp getSellByDate() throws SQLException {
        ProductTyp __jPt_temp = this;
        Timestamp __jPt_result;

        ConnectionContext __sJT_connCtx = getConnectionContext();
        if (__sJT_connCtx == null)
            RuntimeRefErrors.raise_NULL_CONN_CTX();

        ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();

        if (__sJT_execCtx == null)
            RuntimeRefErrors.raise_NULL_EXEC_CTX();


        synchronized (__sJT_execCtx) {
            RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, ProductTyp_SJProfileKeys.getKey(0), 0);
            try {
                __sJT_stmt.setObject(2, __jPt_temp);
                __sJT_execCtx.executeUpdate();
                __jPt_result = __sJT_stmt.getTimestamp(1);
            }
            finally {
                __sJT_execCtx.releaseStatement();
            }
        }

        return __jPt_result;
    }
}

class ProductTyp_SJProfileKeys {
    private static ProductTyp_SJProfileKeys inst = null;

    public static java.lang.Object getKey(int keyNum) throws SQLException {
        if (inst == null) {
            inst = new ProductTyp_SJProfileKeys();
        }
        return inst.keys[keyNum];
    }

    private Object[] keys;

    private ProductTyp_SJProfileKeys() throws SQLException {
        Loader loader = RuntimeContext.getRuntime().getLoaderForClass(getClass());
        keys = new Object[1];
        keys[0] = DefaultContext.getProfileKey(loader, "ProductTyp_SJProfile0");
    }
}
