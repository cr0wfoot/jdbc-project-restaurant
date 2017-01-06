package com.restaurant.controller.dao;

import com.restaurant.model.Ingredient;
import com.restaurant.model.search.criteria.IngredientSearchCriteria;
import java.util.List;

public interface IngredientDao {
    
    /**
     * Save the object of class Horse
     * @param order object of class Order
     */
    void insert(Ingredient order);
    
    /**
     * Get the object of class Horse by id
     * @param id the int value of id
     * @return object of class Horse 
     */
    Ingredient select(Integer id);
    
    /**
     * Get all objects of class Horse
     * @return the list of objects
     */
    List<Ingredient> selectAll();
    
    /**
     * Update the object of class Horse
     * @param horse object of class Horse
     */
    void update(Ingredient order);
    
    /**
     * Delete the object of class Horse by it's id
     * @param id the int value of id
     */
    void delete(Integer id);
    
    List<Ingredient> findByCriteria(IngredientSearchCriteria criteria);
    
}
