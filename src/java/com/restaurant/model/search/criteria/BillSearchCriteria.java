package com.restaurant.model.search.criteria;

import com.restaurant.model.Bill;

public class BillSearchCriteria {
    
    private Integer orderId;
    private Integer userId;
    private Double minPrice;
    private Double maxPrice;
    private String info;

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }
    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public boolean isEmpty() {
        return minPrice == null && maxPrice == null && 
               orderId == null && userId == null &&
               info == null;
    }
    
    public boolean accept(Bill bill) {
        return acceptMinPrice( bill.getPrice()  )
            && acceptMaxPrice( bill.getPrice()  )
            && acceptUserId(   bill.getUserId() )
            && acceptOrderId(  bill.getId()     )
            && acceptInfo(     bill.getInfo()   );
    }
    
    private boolean acceptOrderId(Integer orderId) {
        return this.orderId == null || this.orderId.equals(orderId);
    }
    
    private boolean acceptInfo(String info) {
        return this.info == null || this.info.equals(info);
    }
    
    private boolean acceptUserId(Integer userId) {
        return this.userId == null || this.userId.equals(userId);
    }
    
    private boolean acceptMinPrice(Double price) {
        return this.minPrice == null || this.minPrice.equals(price);
    }
    
    private boolean acceptMaxPrice(Double price) {
        return this.maxPrice == null || this.maxPrice.equals(price);
    }
    
}
