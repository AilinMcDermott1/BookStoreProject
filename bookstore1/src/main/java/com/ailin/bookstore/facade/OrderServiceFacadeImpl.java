package com.ailin.bookstore.facade;

import com.ailin.bookstore.model.Book;
import com.ailin.bookstore.service.InventoryService;
import com.ailin.bookstore.service.PaymentService;

public class OrderServiceFacadeImpl implements OrderServiceFacade {
	public OrderServiceFacadeImpl() {
		// TODO Auto-generated constructor stub
	}
	
	InventoryService inventoryService;
	
	PaymentService paymentService;

	@Override
	public boolean placeOrder(Long id) {
		boolean orderFulfilled = false;
		Book book = new Book();
		id = book.getId();
		
		if(InventoryService.isAvailable(book)) {
			System.out.println("Product with ID: "+ book.getId()+" is available.");
            boolean paymentConfirmed= PaymentService.makePayment();
            if(paymentConfirmed){
                System.out.println("Payment confirmed...");
                orderFulfilled=true;
            }
		}
		return orderFulfilled;
	}

}
