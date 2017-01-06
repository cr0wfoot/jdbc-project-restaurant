package com.restaurant.model;

public class Bill {
    
    private Integer id;
    private Integer userId;
    private Double price;
    private String info;

    public Bill() {
        
    }
    
    public Bill(Integer id, Double price, Integer userId, String info) {
        this.id = id;
        this.info = info;
        this.price = price;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
}
