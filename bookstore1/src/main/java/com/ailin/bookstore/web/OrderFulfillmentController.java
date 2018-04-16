package com.ailin.bookstore.web;

import com.ailin.bookstore.facade.OrderServiceFacade;

public class OrderFulfillmentController {
	
    OrderServiceFacade facade;
    boolean orderFulfilled=false;
    
    public void orderProduct(Long id) {
        orderFulfilled=facade.placeOrder(id);
    }
}
