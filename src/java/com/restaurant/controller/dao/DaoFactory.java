/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurant.controller.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is highest level of abstraction for all Data Access Objects with an associated response object
 * Every data access object must register with a service before it can be used
 * @see HorseDAO
 * @see RaceDAO
 * @see RateDAO
 * @see UserDAO
 * @see MessageDAO
 * @see JDBCDaoFactory
 */
public abstract class DaoFactory {

    /**
     * return the HorseSAO Object
     */
    public abstract OrderDao createOrderDao();
    
    /**
     * return the RaceDAO Object
     */
    public abstract BillDao createBillDao();
    
    /**
     * return the RateDAO Object
     */
    public abstract IngredientDao createIngredientDao();
    
    /**
     * return the UserDAO Object
     */
    public abstract UserDao createUserDao();
    
    /**
     * gets an instance of JDBCDaoFactory
     */
    public static DaoFactory getInstanceJDBC(){
        try {
            return (DaoFactory)Class.forName("com.restaurant.controller.dao.jdbc.JdbcDaoFactory").newInstance();
        } catch (Exception ex) {
            Logger.getLogger(DaoFactory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
}

