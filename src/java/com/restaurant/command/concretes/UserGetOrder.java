package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import com.restaurant.model.Order;
import javax.servlet.http.HttpServletRequest;

public class UserGetOrder extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        Order order = factory.createOrderDao().select(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("order", order);
        request.setAttribute("ingredients", order.getIngredients());
        return "user-order";
    }
    
}
