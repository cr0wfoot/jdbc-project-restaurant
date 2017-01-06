package com.restaurant.model;

public class Ingredient {
    
    private Integer id;
    private String name;
    private String consist;
    private Integer weight;
    private Integer calorie;
    private Double price;
    
    public Ingredient() {
        
    }

    public Ingredient(Integer id, String name, String consist, Integer weight, Integer calorie, Double price) {
        this.id = id;
        this.name = name;
        this.consist = consist;
        this.weight = weight;
        this.calorie = calorie;
        this.price = price;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public String getConsist() {
        return consist;
    }

    public void setConsist(String consist) {
        this.consist = consist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    
}
