package com.restaurant.controller.dao;

import com.restaurant.model.User;
import com.restaurant.model.search.criteria.UserSearchCriteria;
import java.util.List;

public interface UserDao {
    
    /**
     * Save the object of class Horse
     * @param order object of class Order
     */
    void insert(User order);
    
    /**
     * Get the object of class Horse by id
     * @param id the int value of id
     * @return object of class Horse 
     */
    User select(Integer id);
    
    /**
     * Get all objects of class Horse
     * @return the list of objects
     */
    List<User> selectAll();
    
    /**
     * Update the object of class Horse
     * @param horse object of class Horse
     */
    void update(User order);
    
    /**
     * Delete the object of class Horse by it's id
     * @param id the int value of id
     */
    void delete(Integer id);
    
    List<User> findByCriteria(UserSearchCriteria criteria);
    
}
