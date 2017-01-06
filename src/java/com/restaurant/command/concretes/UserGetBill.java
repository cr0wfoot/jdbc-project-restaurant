package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import com.restaurant.model.Bill;
import javax.servlet.http.HttpServletRequest;

public class UserGetBill extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        Bill bill = factory.createBillDao().select(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("bill", bill);
        return "user-bill";
    }
    
}
