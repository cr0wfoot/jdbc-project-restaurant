package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import com.restaurant.model.User;
import javax.servlet.http.HttpServletRequest;

public class AdminUpdateUsers extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        User user = factory.createUserDao().select(Integer.parseInt(request.getParameter("id")));
        user.setAccess(User.Access.valueOf(request.getParameter("access").toUpperCase()));
        factory.createUserDao().update(user);        
        return new AdminGetUsers().doOperation(request);
    }
    
}
