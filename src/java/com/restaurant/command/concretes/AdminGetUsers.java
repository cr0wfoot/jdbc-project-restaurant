package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import com.restaurant.model.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AdminGetUsers extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        List<User> users = factory.createUserDao().selectAll();
        request.setAttribute("users", users);
        return "admin-users";
    }
    
}
