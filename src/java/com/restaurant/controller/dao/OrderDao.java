package com.restaurant.controller.dao;

import com.restaurant.model.Order;
import com.restaurant.model.search.criteria.OrderSearchCriteria;
import java.util.List;

public interface OrderDao {
    
    /**
     * Save the object of class Horse
     * @param order object of class Order
     */
    void insert(Order order);
    
    /**
     * Get the object of class Horse by id
     * @param id the int value of id
     * @return object of class Horse 
     */
    Order select(Integer id);
    
    /**
     * Get all objects of class Horse
     * @return the list of objects
     */
    List<Order> selectAll();
    
    /**
     * Update the object of class Horse
     * @param horse object of class Horse
     */
    void update(Order order);
    
    /**
     * Delete the object of class Horse by it's id
     * @param id the int value of id
     */
    void delete(Integer id);
    
    List<Order> findByCriteria(OrderSearchCriteria criteria);
    
}
