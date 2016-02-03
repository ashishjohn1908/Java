package com.tddinaction.data.spring.jdbc;

import com.tddinaction.data.dbunit.InMemoryDatabaseTestCase;
import com.tddinaction.data.person.Person;
import junit.framework.JUnit4TestAdapter;
import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.Assertion;
import org.dbunit.dataset.*;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplatePersonDaoIntegrationTest extends  InMemoryDatabaseTestCase {

    private List<Person> expectedList;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(JdbcTemplatePersonDaoIntegrationTest.class);
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        Person person;
        expectedList = new ArrayList<Person>();
        person = new Person("Drew", "Smith");
        person.setSsn("000-68-0000");
        expectedList.add(person);
        person = new Person("Nick", "Marquiss");
        person.setSsn("000-69-0000");
        expectedList.add(person);
        person = new Person("Jose", "Whitson");
        person.setSsn("000-70-0000");
        expectedList.add(person);
    }

    @Test
    public void testFindAll() throws Exception {
        JdbcTemplatePersonDao dao = new JdbcTemplatePersonDao();
        dao.setDataSource(new BasicDataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return getJdbcConnection();
            }

            public <T> T unwrap(Class<T> iface) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        dao.save(expectedList);
        assertEquals(expectedList, dao.findAll());
    }

    @Test
    public void testSavePerson() throws Exception {
        JdbcTemplatePersonDao dao = new JdbcTemplatePersonDao();
        dao.setDataSource(new BasicDataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return getJdbcConnection();
            }

            public <T> T unwrap(Class<T> iface) throws SQLException {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }

            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return false;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        Person person = new Person("John", "Doe");
        person.setSsn("000-69-0000");
        dao.save(person);

        IDataSet expectedData = getDataSet("afterSavePerson");
        String[] tables = new String[] { "employee", "salary" };

        IDataSet actualData = getConnection().createDataSet(tables);

        // exclude start_date columns from this comparison
        ITable actualTable = getTable(actualData, "employee", "start_date");
        ITable expectedTable = getTable(expectedData, "employee", "start_date");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    private ITable getTable(IDataSet dataset, String table, String... excludedColumns) throws DataSetException {
        DefaultColumnFilter columnFilter = new DefaultColumnFilter();
        for (String column : excludedColumns) {
            columnFilter.excludeColumn(column);
        }
        FilteredTableMetaData md = new FilteredTableMetaData(dataset.getTableMetaData(table), columnFilter);
        return new CompositeTable(md, dataset.getTable(table));
    }
}
