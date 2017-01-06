package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import com.restaurant.model.Ingredient;
import com.restaurant.model.Order;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class UserInsertOrder extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        Order order = new Order();
        order.setTime        ( new Date()                );
        order.setUserId      ( getUserId(request)        );
        order.setIngredients ( getIngredients(request)   );
        factory.createOrderDao().insert(order);
        return new UserMainPage().doOperation(request);
    }
    
    private List<Ingredient> getIngredients(HttpServletRequest request) {
        List<Ingredient> ingredients = new LinkedList<Ingredient>();
        String[] ingredientsId = request.getParameterValues("ingredients");
        for(int j = 0; j < ingredientsId.length; j++) {
            Ingredient i = new Ingredient();
            i.setId(Integer.parseInt(ingredientsId[j]));
            ingredients.add(i);
        }
        return ingredients;
    }
    
}
