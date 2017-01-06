package com.restaurant.controller.dao;

import com.restaurant.model.Bill;
import com.restaurant.model.search.criteria.BillSearchCriteria;
import java.util.List;

public interface BillDao {
    
    /**
     * Save the object of class Horse
     * @param order object of class Order
     */
    void insert(Bill bill);
    
    /**
     * Get the object of class Horse by id
     * @param id the int value of id
     * @return object of class Horse 
     */
    Bill select(Integer id);
    
    /**
     * Get all objects of class Horse
     * @return the list of objects
     */
    List<Bill> selectAll();
    
    /**
     * Update the object of class Horse
     * @param horse object of class Horse
     */
    void update(Bill bill);
    
    /**
     * Delete the object of class Horse by it's id
     * @param id the int value of id
     */
    void delete(Integer id);
    
    List<Bill> findByCriteria(BillSearchCriteria criteria);
    
}
