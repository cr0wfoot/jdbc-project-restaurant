package com.restaurant.model.search.criteria;

import com.restaurant.model.User;

public class UserSearchCriteria {
    
    private String access;
    private String login;
    private String name;

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isEmpty() {
        return name == null && access == null && login == null;
    }
    
    public boolean accept(User user) {
        return acceptLogin(  user.getLogin()                       )
            && acceptAccess( user.getAccess().name().toLowerCase() )
            && acceptName(   user.getName()                        );
    }
    
    private boolean acceptName(String name) {
        return this.name == null || this.name.equals(name);
    }
    
    private boolean acceptLogin(String login) {
        return this.login == null || this.login.equals(login);
    }
    
    private boolean acceptAccess(String access) {
        return this.access == null || this.access.equals(access);
    }
    
}
