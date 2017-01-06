package com.restaurant.controller.dao.jdbc;

import com.restaurant.controller.dao.OrderDao;
import com.restaurant.model.Ingredient;
import com.restaurant.model.Order;
import com.restaurant.model.search.criteria.OrderSearchCriteria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class OrderJdbc implements OrderDao {
    
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;
    
    public void setDataSource(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
        this.namedTemplate = new NamedParameterJdbcTemplate(ds);
    }
    
    private ResultSetExtractor<List<Order>> extractor = new ResultSetExtractor() {
        @Override
        public List<Order> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Integer, Order> map = new HashMap<Integer, Order>();
            Order order;
            while (rs.next()) {
            	Integer id = rs.getInt("orders.id");
            	order = map.get(id);
                if(order == null){
                    order = new Order(id, rs.getDate("time"), rs.getInt("id_user"));
                    map.put(id, order);
                }
	        Ingredient ingredient = new Ingredient(
                    rs.getInt("ingredients.id"), rs.getString("name"),
                    rs.getString("consist"), rs.getInt("weight"),
                    rs.getInt("calorie"), rs.getDouble("price"));
                order.addIngredient(ingredient);
            }
            return new LinkedList<Order>(map.values());
        }
    };
    
    private PreparedStatementCreator getPreparedStatementCreator(final Order order, final String sql) {
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                int i = 0;
                ps.setInt(    ++i, order.getUserId()                            );
                ps.setDate(   ++i, new java.sql.Date(order.getTime().getTime()) );
                return ps;
            }
        };
    }
    
    private PreparedStatementSetter getPreparedStatementSetter(final Order order) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 0;
                ps.setInt(    ++i, order.getUserId()                            );
                ps.setDate(   ++i, new java.sql.Date(order.getTime().getTime()) );
                ps.setInt(    ++i, order.getId()                                );
            }           
        };
    }
    
    private BatchPreparedStatementSetter getBatchPreparedStatementSetter(final int orderId, final List<Ingredient> ingredients) {
        return new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Ingredient ingredient = ingredients.get(i);
                int j = 0;
                ps.setInt( ++j, orderId            );
                ps.setInt( ++j, ingredient.getId() );
            }
            @Override
            public int getBatchSize() {
                return ingredients.size();
            }
        };
    }

    @Override
    public void insert(Order order) {
        String sqlOrder = "INSERT INTO orders(id_user, time, status) VALUES(? , ?, ?)";
        String sqlOrderIngredients = "INSERT INTO order_ingredient(id_order, id_ingredient) VALUES(? , ?)";
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(getPreparedStatementCreator(order, sqlOrder), key);
        jdbcTemplate.batchUpdate(sqlOrderIngredients, getBatchPreparedStatementSetter(key.getKey().intValue(), order.getIngredients()));
    }

    @Override
    public Order select(Integer id) {
        String sql = "SELECT orders.*,ingredients.* FROM orders " +
                     "INNER JOIN order_ingredient ON orders.id = order_ingredient.id_order " +
                     "INNER JOIN ingredients      ON order_ingredient.id_ingredient = ingredients.id " +
                     "WHERE orders.id=?";
        return jdbcTemplate.query(sql, extractor, id).get(0);
    }

    @Override
    public List<Order> selectAll() {
        String sql = "SELECT orders.*,ingredients.* FROM orders " +
                     "INNER JOIN order_ingredient ON orders.id = order_ingredient.id_order " +
                     "INNER JOIN ingredients      ON order_ingredient.id_ingredient = ingredients.id ";
        return jdbcTemplate.query(sql, extractor);
    }

    @Override
    public void update(Order order) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM orders WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Order> findByCriteria(OrderSearchCriteria criteria) {
        if(criteria.isEmpty())
            return selectAll();
        String sql = "SELECT orders.*,ingredients.* FROM orders " +
                     "INNER JOIN order_ingredient ON orders.id = order_ingredient.id_order " +
                     "INNER JOIN ingredients      ON order_ingredient.id_ingredient = ingredients.id " +
                     "WHERE true";
        if(criteria.getUserId() != null)
            sql += " AND orders.id_user=:userId";
        if(criteria.getMaxTimeRelease() != null)
            sql += " AND orders.time<:maxTime";
        if(criteria.getMinTimeRelease() != null)
            sql += " AND orders.time>:minTime";
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(criteria);
        return namedTemplate.query(sql, namedParameters, extractor);
    }
    
}
