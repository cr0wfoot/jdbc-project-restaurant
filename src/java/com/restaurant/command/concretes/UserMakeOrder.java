package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import com.restaurant.model.Ingredient;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class UserMakeOrder extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        List<Ingredient> ingredients = factory.createIngredientDao().selectAll();
        request.setAttribute("ingredients", ingredients);
        return "user-make-order";
    }
    
}
