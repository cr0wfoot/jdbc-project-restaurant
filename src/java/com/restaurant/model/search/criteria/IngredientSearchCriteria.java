package com.restaurant.model.search.criteria;

import com.restaurant.model.Ingredient;

public class IngredientSearchCriteria {
    
    private String name;
    private String consist;
    private Integer maxWeight;
    private Integer minWeight;
    private Integer maxCalorie;
    private Integer minCalorie;
    private Double maxPrice;
    private Double minPrice;

    public String getConsist() {
        return consist;
    }

    public void setConsist(String consist) {
        this.consist = consist;
    }

    public Integer getMaxCalorie() {
        return maxCalorie;
    }

    public void setMaxCalorie(Integer maxCalorie) {
        this.maxCalorie = maxCalorie;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Integer getMinCalorie() {
        return minCalorie;
    }

    public void setMinCalorie(Integer minCalorie) {
        this.minCalorie = minCalorie;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(Integer minWeight) {
        this.minWeight = minWeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isEmpty() {
        return name == null && consist == null && maxWeight == null
            && minWeight == null && maxCalorie == null && minCalorie == null
            && maxPrice == null && minPrice == null;
    }
    
    public boolean accept(Ingredient ingredient) {
        return acceptMinPrice(   ingredient.getPrice()   )
            && acceptMaxPrice(   ingredient.getPrice()   )
            && acceptMaxCalorie( ingredient.getCalorie() )
            && acceptMinCalorie( ingredient.getCalorie() )
            && acceptMaxWeight(  ingredient.getWeight()  )
            && acceptMinWeight(  ingredient.getWeight()  )
            && acceptConsist(    ingredient.getConsist() )
            && acceptName(       ingredient.getName()    );
    }
    
    private boolean acceptName(String name) {
        return this.name == null || this.name.equals(name);
    }
    
    private boolean acceptConsist(String consist) {
        return this.consist == null || this.consist.equals(consist);
    }
    
    private boolean acceptMaxWeight(Integer weight) {
        return this.maxWeight == null || this.maxWeight.equals(weight);
    }
    
    private boolean acceptMinWeight(Integer weight) {
        return this.minWeight == null || this.minWeight.equals(weight);
    }
    
    private boolean acceptMaxCalorie(Integer calorie) {
        return this.maxCalorie == null || this.maxCalorie.equals(calorie);
    }
    
    private boolean acceptMinCalorie(Integer calorie) {
        return this.minCalorie == null || this.minCalorie.equals(calorie);
    }
    
    private boolean acceptMaxPrice(Double price) {
        return this.maxPrice == null || this.maxPrice.equals(price);
    }
    
    private boolean acceptMinPrice(Double price) {
        return this.minPrice == null || this.minPrice.equals(price);
    }
    
}
