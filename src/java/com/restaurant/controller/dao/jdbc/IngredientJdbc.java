package com.restaurant.controller.dao.jdbc;

import com.restaurant.controller.dao.IngredientDao;
import com.restaurant.model.Ingredient;
import com.restaurant.model.search.criteria.IngredientSearchCriteria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class IngredientJdbc implements IngredientDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;
    
    public void setDataSource(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
        this.namedTemplate = new NamedParameterJdbcTemplate(ds);
    }
    
    private RowMapper<Ingredient> rowMapper = new RowMapper<Ingredient>() {
        @Override
        public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(      rs.getInt("id")         );
            ingredient.setName(    rs.getString("name")    );
            ingredient.setConsist( rs.getString("consist") );
            ingredient.setCalorie( rs.getInt("calorie")    );
            ingredient.setWeight(  rs.getInt("weight")     );
            ingredient.setPrice(   rs.getDouble("price")   );
            return ingredient;
        }       
    };
    
    private PreparedStatementSetter getPreparedStatementSetter(final Ingredient ingredient) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 0;
                ps.setString( ++i, ingredient.getName()    );
                ps.setString( ++i, ingredient.getConsist() );
                ps.setInt(    ++i, ingredient.getCalorie() );
                ps.setInt(    ++i, ingredient.getWeight()  );
                ps.setDouble( ++i, ingredient.getPrice()   );
                ps.setInt(    ++i, ingredient.getId()      );
            }           
        };
    }
    
    @Override
    public void insert(Ingredient ingredient) {
        String sql = "INSERT INTO ingredients(name, consist, calorie, weight, price) VALUES(? , ? , ? , ?, ?)";
        jdbcTemplate.update(sql, getPreparedStatementSetter(ingredient));
    }

    @Override
    public Ingredient select(Integer id) {
        String sql = "SELECT * FROM ingredients WHERE id=?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Ingredient> selectAll() {
        String sql = "SELECT * FROM ingredients";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void update(Ingredient ingredient) {
        String sql = "UPDATE ingredients SET name=?, consist=?, calorie=?, weight=?, price=? WHERE id=?";
        jdbcTemplate.update(sql, getPreparedStatementSetter(ingredient));
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM ingredients WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Ingredient> findByCriteria(IngredientSearchCriteria criteria) {
        if(criteria.isEmpty())
            return selectAll();
        String sql = "SELECT * FROM ingredients WHERE true";
        if(criteria.getConsist() != null)
            sql += " AND ingredients.consist=:consist";
        if(criteria.getName() != null)
            sql += " AND ingredients.name=:name";
        if(criteria.getMaxCalorie() != null)
            sql += " AND ingredients.calorie<:maxCalorie";
        if(criteria.getMinCalorie() != null)
            sql += " AND ingredients.calorie>:minCalorie";
        if(criteria.getMaxPrice() != null)
            sql += " AND ingredients.price<:maxPrice";
        if(criteria.getMinPrice() != null)
            sql += " AND ingredients.price>:minPrice";
        if(criteria.getMaxWeight() != null)
            sql += " AND ingredients.weight<:maxWeight";
        if(criteria.getMinWeight() != null)
            sql += " AND ingredients.weight>:minWeight";

        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(criteria);
        return namedTemplate.query(sql, namedParameters, rowMapper);
    }
    
}
