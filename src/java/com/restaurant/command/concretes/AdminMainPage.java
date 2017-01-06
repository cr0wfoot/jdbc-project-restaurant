package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import javax.servlet.http.HttpServletRequest;

public class AdminMainPage extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        request.setAttribute("orders", factory.createOrderDao().selectAll());
        return "admin-index";
    }
    
}
