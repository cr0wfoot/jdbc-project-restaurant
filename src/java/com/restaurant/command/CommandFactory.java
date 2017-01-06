package com.restaurant.command;

import com.restaurant.command.concretes.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandFactory {
    
    /**
     * An instance of this object
     */
    protected static CommandFactory instance = null;
    
    /**
     * Get the instance of this object
     * @return the value of {@link JDBCConnection#instance}
     */
    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }
    
    /**
     * Enumeration of commands
     */
    enum ConcreteCommand { 
        
        /**
         * Store "log in" command
         * @see VisitorLogin
         */
        LOGIN(            new VisitorLogin()     ), 
        
        /**
         * Store "register" command
         * @see VisitorRegister
         */
        REGISTER(         new VisitorRegister()  ),
        
        /**
         * Store "get race and all horses for admin" command
         * @see AdminRaceGet
         */
        USR_MAIN_PAGE(    new UserMainPage()     ),
        
        /**
         * Store "fix race and set results" command
         * @see AdminRaceFixResults
         */
        USR_GET_ORDER(    new UserGetOrder()     ),
        
        /**
         * Store "update race" command
         * @see AdminRaceUpdate
         */
        USR_GET_BILL(     new UserGetBill()      ),
        
        /**
         * Store "delete race" command
         * @see AdminRaceDelete
         */
        USR_PAY_BILL(     new UserPayBill()      ),
        
        /**
         * Store "insert race" command
         * @see AdminRaceNew
         */
        USR_MAKE_ORDER(   new UserMakeOrder()    ),
        
        /**
         * Store "insert horse" command
         * @see AdminHorseNew
         */
        USR_INSERT_ORDER( new UserInsertOrder()  ),
        
        /**
         * Store "delete or update horse" command
         * @see AdminHorseUpdate
         */
        ADM_MAIN_PAGE(    new AdminMainPage()    ),
        
        /**
         * Store "delete or update user" command
         * @see AdminUserUpdate
         */
        ADM_GET_ORDER(    new AdminGetOrder()    ),
        
        /**
         * Store "delete or update user" command
         * @see AdminUserUpdate
         */
        ADM_ACCEPT_ORDER( new AdminAcceptOrder() ),
        
        /**
         * Store "delete or update user" command
         * @see AdminUserUpdate
         */
        ADM_GET_USERS(    new AdminGetUsers()    ),
        
        /**
         * Store "delete or update user" command
         * @see AdminUserUpdate
         */
        ADM_UPDATE_USERS( new AdminUpdateUsers() ),
        
        /**
         * Store "delete or update user" command
         * @see AdminUserUpdate
         */
        ADM_DEL_USER(     new AdminDeleteUser()  );
       
        /**
         * An object of type Command
         */
        private final Command command;
        
        /**
         * Constructor with one argument initialize field {@link CommandType#command}
         * @param command    an object of type Command
         */
        ConcreteCommand(Command command) {
            this.command = command;
        }
        
    }
    
    /**
     * Get an object of type Command
     * @param command
     * the name of command
     * @return the value of {@link CommandType#command}
     */
    public Command getCommand(String command) {
        try {
            ConcreteCommand ct = ConcreteCommand.valueOf(command.toUpperCase());
            return ct.command;
        } catch(IllegalArgumentException ex) {
            return new Command() {
                @Override
                public void execute(HttpServletRequest request, HttpServletResponse response) {
                    try {
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    } catch (ServletException ex1) {
                        Logger.getLogger(CommandFactory.class.getName()).log(Level.SEVERE, null, ex1);
                    } catch (IOException ex1) {
                        Logger.getLogger(CommandFactory.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            };
        }
    }
    
}
