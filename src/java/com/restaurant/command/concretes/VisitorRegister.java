package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import com.restaurant.model.User;
import com.restaurant.model.search.criteria.UserSearchCriteria;
import javax.servlet.http.HttpServletRequest;

public class VisitorRegister extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        UserSearchCriteria criteria = new UserSearchCriteria();
        criteria.setLogin(login);
        if(!factory.createUserDao().findByCriteria(criteria).isEmpty()) {
            return "error";
        } else {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setName(name);
            user.setAccess(User.Access.USER);
            factory.createUserDao().insert(user);
        }
        return "index";
    }
    
}
