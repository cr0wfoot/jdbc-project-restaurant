package com.restaurant.model;

public class User {
    
    private Integer id;
    private String login;
    private String password;
    private String name;
    private Access access;

    public User() {
        
    }
    
    public User(Integer id, String login, String password, String name, Access access) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.access = access;
    }
    
    public enum Access {
        
        ADMIN,
        USER,
        
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
            
}
