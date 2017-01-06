package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import javax.servlet.http.HttpServletRequest;

public class AdminDeleteUser extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        factory.createUserDao().delete(Integer.parseInt(request.getParameter("id")));
        return new AdminGetUsers().doOperation(request);
    }
    
}
