package com.tddinaction.data.spring.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.GregorianCalendar;
import java.util.Calendar;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.tddinaction.data.PersonDao;
import com.tddinaction.data.person.Person;
import sun.util.calendar.LocalGregorianCalendar;
import sun.util.calendar.BaseCalendar;

public class JdbcTemplatePersonDao extends JdbcDaoSupport implements PersonDao {

    @SuppressWarnings("unchecked")
    public List<Person> findAll() {
        return getJdbcTemplate().query("SELECT * FROM employee", new Object[0], new PersonRowMapper());
    }

    @SuppressWarnings("unchecked")
    public List<Person> findByLastName(String lastName) {
        String sql = "SELECT * FROM employee WHERE last_name = ?";
        Object[] args = { lastName };
        RowMapper mapper = new PersonRowMapper();
        return getJdbcTemplate().query(sql, args, mapper);
    }

    @SuppressWarnings("unchecked")
    public List<Person> findByLastname(String lastname) {
        String sql = "SELECT * FROM employee WHERE last_name = ?";
        String[] args = new String[] { lastname };
        RowMapper mapper = new PersonRowMapper();
        return getJdbcTemplate().query(sql, args, mapper);
    }

    public void save(final Person person) {
        getJdbcTemplate().execute("INSERT INTO employee (employee_uid, start_date, first_name, last_name, ssn) VALUES (?,?, ?, ?, ?)",
                                    new PreparedStatementCallback() {
                                         public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                                            ps.setInt(1, 5);
                                           //ps.setString(2, "to_date('10-jun-2008','dd-mon-yyyy')");
                                            ps.setDate(2, new Date(new GregorianCalendar().getTimeInMillis()));
                                            ps.setString(3, person.getFirstname());
                                            ps.setString(4, person.getLastname());
                                            ps.setString(5, person.getSsn());
                                            ps.executeUpdate();
                                            return null;
                                        }
                                    }
                                 );
    }

    public void save(final List<Person> persons) {
        int i = 0;
        for(final Person person: persons) {
        i++;
        final int count = i;
        getJdbcTemplate().execute("INSERT INTO employee (employee_uid, start_date, first_name, last_name, ssn) VALUES (?,?, ?, ?, ?)",
                                    new PreparedStatementCallback() {
                                       

                                        public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                                            ps.setInt(1, 5+count);
                                           //ps.setString(2, "to_date('10-jun-2008','dd-mon-yyyy')");
                                            ps.setDate(2, new Date(new GregorianCalendar().getTimeInMillis()));
                                            ps.setString(3, person.getFirstname());
                                            ps.setString(4, person.getLastname());
                                            ps.setString(5, person.getSsn());
                                            ps.executeUpdate();
                                            return null;
                                        }
                                    }
                                 );
        }
    }

    public Person find(Long id) {
        return null;
    }

}
