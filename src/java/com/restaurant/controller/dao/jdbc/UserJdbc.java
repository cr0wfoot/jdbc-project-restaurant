package com.restaurant.controller.dao.jdbc;

import com.restaurant.controller.dao.UserDao;
import com.restaurant.model.User;
import com.restaurant.model.search.criteria.UserSearchCriteria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class UserJdbc implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;
    
    public void setDataSource(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
        this.namedTemplate = new NamedParameterJdbcTemplate(ds);
    }
    
    private RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(       rs.getInt("id")                                          );
            user.setAccess(   User.Access.valueOf(rs.getString("access").toUpperCase() ) );
            user.setLogin(    rs.getString("login")                                    );
            user.setPassword( rs.getString("password")                                 );
            user.setName(     rs.getString("name")                                     );
            return user;
        }       
    };
    
    private PreparedStatementCreator getPreparedStatementCreator(final User user, final String sql) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                int i = 0;
                ps.setString( ++i, user.getLogin()                       );
                ps.setString( ++i, user.getPassword()                    );
                ps.setString( ++i, user.getName()                        );
                ps.setString( ++i, user.getAccess().name().toLowerCase() );
                return ps;
            }
        };
    }
    
    private PreparedStatementSetter getPreparedStatementSetter(final User user) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 0;
                ps.setString( ++i, user.getLogin()                       );
                ps.setString( ++i, user.getPassword()                    );
                ps.setString( ++i, user.getName()                        );
                ps.setString( ++i, user.getAccess().name().toLowerCase() );
                ps.setInt(    ++i, user.getId()                          );
            }           
        };
    }
    
    @Override
    public void insert(User user) {
        String sql = "INSERT INTO users(login, password, name, access) VALUES(? , ? , ? , ?)";
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(getPreparedStatementCreator(user, sql), key);
    }

    @Override
    public User select(Integer id) {
        String sql = "SELECT * FROM users WHERE id=?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<User> selectAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET login=?, password=?, name=?, access=? WHERE id=?";
        jdbcTemplate.update(sql, getPreparedStatementSetter(user));
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM users WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<User> findByCriteria(UserSearchCriteria criteria) {
        if(criteria.isEmpty())
            return selectAll();
        String sql = "SELECT * FROM users WHERE true";
        if(criteria.getLogin() != null)
            sql += " AND users.login=:login";
        if(criteria.getAccess() != null)
            sql += " AND users.access=:access";
        if(criteria.getName() != null)
            sql += " AND users.name=:name";

        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(criteria);
        return namedTemplate.query(sql, namedParameters, rowMapper);
    }
    
}
