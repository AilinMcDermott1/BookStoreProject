package com.ailin.bookstore.facade;

import com.ailin.bookstore.model.Book;
import com.ailin.bookstore.service.InventoryService;
import com.ailin.bookstore.service.PaymentService;

public class OrderServiceFacadeImpl implements OrderServiceFacade {
	  public boolean placeOrder(Long id){
	        boolean orderFulfilled=false;
	        Book book=new Book();
	        book.id=id;
	        if(InventoryService.isAvailable(book))
	        {
	            System.out.println("Book with ID: "+ book.id+" is available.");
	            boolean paymentConfirmed= PaymentService.makePayment();
	            if(paymentConfirmed){
	                System.out.println("Payment confirmed...");
	                orderFulfilled=true;
	            }
	        }
	        return orderFulfilled;
	    }

	@Override
	public boolean placeOrder(int id) {
		return false;
	}

}
