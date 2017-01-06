package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import javax.servlet.http.HttpServletRequest;

public class UserPayBill extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        factory.createBillDao().delete(Integer.parseInt(request.getParameter("id")));
        return new UserMainPage().doOperation(request);
    }
    
}
