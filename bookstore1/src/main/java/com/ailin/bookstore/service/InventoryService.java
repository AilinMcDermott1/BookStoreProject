package com.ailin.bookstore.service;

import com.ailin.bookstore.model.Book;

public class InventoryService {
public static boolean isAvailable(Book book) {
		
		boolean available = false;
		
		if (book.getQuantity() > 0) {
			available = true;
			//System.out.println(book.getTitle() + " is available");
		} else if (book.getQuantity()== 0){
			available = false;
			//System.out.println(book.getTitle() + " is out of stock");
		}
		return available;
	}

}
