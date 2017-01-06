package com.restaurant.controller.dao.jdbc;

import com.restaurant.controller.dao.BillDao;
import com.restaurant.model.Bill;
import com.restaurant.model.search.criteria.BillSearchCriteria;
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

public class BillJdbc implements BillDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;
    
    public void setDataSource(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
        this.namedTemplate = new NamedParameterJdbcTemplate(ds);
    }
    
    private RowMapper<Bill> rowMapper = new RowMapper<Bill>() {
        @Override
        public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bill bill = new Bill();
            bill.setId(     rs.getInt("id")       );
            bill.setPrice(  rs.getDouble("total") );
            bill.setUserId( rs.getInt("id_user")  );
            bill.setInfo(   rs.getString("info")  );
            return bill;
        }       
    };
    
    private PreparedStatementCreator getPreparedStatementCreator(final Bill bill, final String sql) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                int i = 0;
                ps.setDouble( ++i, bill.getPrice()   );
                ps.setInt(    ++i, bill.getUserId()  );
                ps.setString( ++i, bill.getInfo()    );
                return ps;
            }
        };
    }
    
    private PreparedStatementSetter getPreparedStatementSetter(final Bill bill) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 0;
                ps.setDouble( ++i, bill.getPrice()   );
                ps.setInt(    ++i, bill.getUserId()  );
                ps.setInt(    ++i, bill.getId()      );
                ps.setString( ++i, bill.getInfo()    );
            }           
        };
    }
    
    @Override
    public void insert(Bill bill) {
        String sql = "INSERT INTO bills(id_order, total, id_user) VALUES(? , ? , ?)";
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(getPreparedStatementCreator(bill, sql), key);
    }

    @Override
    public Bill select(Integer id) {
        String sql = "SELECT * FROM bills WHERE id=?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Bill> selectAll() {
        String sql = "SELECT * FROM bills";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void update(Bill bill) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM bills WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Bill> findByCriteria(BillSearchCriteria criteria) {
        if(criteria.isEmpty())
            return selectAll();
        String sql = "SELECT * FROM bills WHERE true";
        if(criteria.getOrderId() != null)
            sql += " AND bills.id_order=:orderId";
        if(criteria.getUserId() != null)
            sql += " AND bills.id_user=:userId";
        if(criteria.getMinPrice() != null)
            sql += " AND bills.price>:minPrice";
        if(criteria.getMaxPrice() != null)
            sql += " AND bills.price<:maxPrice";
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(criteria);
        return namedTemplate.query(sql, namedParameters, rowMapper);
    }
    
}
