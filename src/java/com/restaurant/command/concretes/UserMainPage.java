package com.restaurant.command.concretes;

import com.restaurant.command.AbstractCommand;
import com.restaurant.model.search.criteria.BillSearchCriteria;
import com.restaurant.model.search.criteria.OrderSearchCriteria;
import javax.servlet.http.HttpServletRequest;

public class UserMainPage extends AbstractCommand {

    @Override
    protected String doOperation(HttpServletRequest request) {
        BillSearchCriteria billCriteria = new BillSearchCriteria();
        OrderSearchCriteria orderCriteria = new OrderSearchCriteria();
        int userId = getUserId(request);
        billCriteria.setUserId(userId);
        orderCriteria.setUserId(userId);
        request.setAttribute("bills", factory.createBillDao().findByCriteria(billCriteria));
        request.setAttribute("orders", factory.createOrderDao().findByCriteria(orderCriteria));
        request.setAttribute("user", factory.createUserDao().select(userId));
        return "user-index";
    }
    
}
