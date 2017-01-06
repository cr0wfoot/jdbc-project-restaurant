package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import com.restaurant.model.Order;
import javax.servlet.http.HttpServletRequest;

public class AdminGetOrder extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        Order order = factory.createOrderDao().select(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("order", order);
        return "admin-order";
    }
    
}
