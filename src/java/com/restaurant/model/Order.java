package com.restaurant.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Order {
    
    private Integer id;
    private Date time;
    private Integer userId;
    private List<Ingredient> ingredients = new LinkedList<Ingredient>();

    public Order() {
        
    }
    
    public Order(Integer id, Date time, Integer userId) {
        this.id = id;
        this.time = time;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    
}
