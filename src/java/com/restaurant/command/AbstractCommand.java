package com.restaurant.command;

import com.restaurant.controller.dao.DaoFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class AbstractCommand implements Command {
    
    protected final DaoFactory factory = DaoFactory.getInstanceJDBC();
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        String url = doOperation(request);        
        try {
            request.getRequestDispatcher(url + ".jsp").forward(request, response);
        } catch(Exception ex) {
             
        }
    }
    
    protected int getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(true);    
        return Integer.parseInt((String)session.getAttribute("user"));
    }
    
    protected abstract String doOperation(HttpServletRequest request);
    
}
