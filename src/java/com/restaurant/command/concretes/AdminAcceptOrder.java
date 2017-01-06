package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import com.restaurant.controller.dao.OrderDao;
import com.restaurant.model.Bill;
import com.restaurant.model.Ingredient;
import com.restaurant.model.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class AdminAcceptOrder extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        OrderDao orderDao = factory.createOrderDao();
        Order order = orderDao.select(Integer.parseInt(request.getParameter("id")));
        Bill bill = new Bill();
        bill.setUserId  ( order.getUserId()                  );
        bill.setPrice   ( countPrice(order.getIngredients()) );
        factory.createBillDao().insert(bill);
        orderDao.delete(order.getId());
        return new AdminMainPage().doOperation(request);
    }
    
    private double countPrice(List<Ingredient> ingredients) {
        double totalPrice = 0;
        for(Ingredient i : ingredients)
            totalPrice += i.getPrice();
        return totalPrice;
    }
    
}
