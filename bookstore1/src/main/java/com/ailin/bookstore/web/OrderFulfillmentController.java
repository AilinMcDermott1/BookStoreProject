package com.ailin.bookstore.web;

import com.ailin.bookstore.facade.OrderServiceFacade;

public class OrderFulfillmentController {
    OrderServiceFacade facade;
    boolean orderFulfilled=false;
    public boolean orderProduct(int productId) {
        orderFulfilled=facade.placeOrder(productId);
        System.out.println("OrderFulfillmentController: Order fulfillment completed. ");
		return orderFulfilled;
    }
}
