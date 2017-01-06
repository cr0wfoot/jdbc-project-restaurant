package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import com.restaurant.controller.dao.UserDao;
import com.restaurant.model.User;
import com.restaurant.model.search.criteria.UserSearchCriteria;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VisitorLogin extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserSearchCriteria criteria = new UserSearchCriteria();
        criteria.setLogin(login);
        UserDao userDao = factory.createUserDao();
        List<User> users = userDao.findByCriteria(criteria);
        User user;
        if(users.isEmpty() || !(user = users.get(0)).getPassword().equals(password)) {
            return "error";
        } else {
            HttpSession session = request.getSession(true);                    
            session.setAttribute("user", String.valueOf(user.getId())); 
            session.setAttribute("access", user.getAccess());
            switch(user.getAccess()) {
                case ADMIN : return new AdminMainPage().doOperation(request);
                case USER  : return new UserMainPage().doOperation(request);
                default    : return "error";
            }
        }
    }
    
}
