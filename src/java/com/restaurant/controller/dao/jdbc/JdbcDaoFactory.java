package com.restaurant.controller.dao.jdbc;

import com.restaurant.controller.dao.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class JdbcDaoFactory extends DaoFactory{

    @Override
    public OrderDao createOrderDao() {
        OrderJdbc jdbc = new OrderJdbc();
        try {
            jdbc.setDataSource((javax.sql.DataSource)(new javax.naming.InitialContext().lookup("jdbc/mysql-restaurant")));
        } catch (NamingException ex) {
            Logger.getLogger(JdbcDaoFactory.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return jdbc;
    }

    @Override
    public BillDao createBillDao() {
        BillJdbc jdbc = new BillJdbc();
        try {
            jdbc.setDataSource((javax.sql.DataSource)(new javax.naming.InitialContext().lookup("jdbc/mysql-restaurant")));
        } catch (NamingException ex) {
            Logger.getLogger(JdbcDaoFactory.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return jdbc;
    }

    @Override
    public IngredientDao createIngredientDao() {
        IngredientJdbc jdbc = new IngredientJdbc();
        try {
            jdbc.setDataSource((javax.sql.DataSource)(new javax.naming.InitialContext().lookup("jdbc/mysql-restaurant")));
        } catch (NamingException ex) {
            Logger.getLogger(JdbcDaoFactory.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return jdbc;
    }

    @Override
    public UserDao createUserDao() {
        UserJdbc jdbc = new UserJdbc();
        try {
            jdbc.setDataSource((javax.sql.DataSource)(new javax.naming.InitialContext().lookup("jdbc/mysql-restaurant")));
        } catch (NamingException ex) {
            Logger.getLogger(JdbcDaoFactory.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return jdbc;
    }

}
