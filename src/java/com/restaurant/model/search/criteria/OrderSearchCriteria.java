package com.restaurant.model.search.criteria;

import com.restaurant.model.Order;
import java.util.Date;

public class OrderSearchCriteria {
    
    private Date minTime;
    private Date maxTime;
    private Integer userId;

    public Date getMaxTimeRelease() {
        return maxTime;
    }

    public void setMaxTimeRelease(Date maxTimeRelease) {
        this.maxTime = maxTimeRelease;
    }

    public Date getMinTimeRelease() {
        return minTime;
    }

    public void setMinTimeRelease(Date minTimeRelease) {
        this.minTime = minTimeRelease;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public boolean isEmpty() {
        return minTime == null && maxTime == null && userId == null;
    }
    
    public boolean accept(Order order) {
        return acceptMinTime( order.getTime()                        )
            && acceptMaxTime( order.getTime()                        )
            && acceptUserId(  order.getId()                          );
    }
    
    private boolean acceptUserId(Integer userId) {
        return this.userId == null || this.userId.equals(userId);
    }
    
    private boolean acceptMinTime(Date time) {
        return this.minTime == null || this.minTime.equals(time);
    }
    
    private boolean acceptMaxTime(Date time) {
        return this.maxTime == null || this.maxTime.equals(time);
    }
    
}
