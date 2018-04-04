package com.example.BookStore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private Long id;
	private String title; 
	private String author; 
	private String category; 
	private double price; 
	
		
	
	public Book() {
		super();
	}
	
	
	public Book(Long id, String title, String author, String category, int price) {
		super();
		this.id = id; 
		this.title = title; 
		this.author = author; 
		this.category = category;
		this.price = price; 
	}
//	
//	public Book(Long id, String title, String author, String category, double price) {
//		super();
//		this.id = id; 
//		this.title = title; 
//		this.author = author; 
//		this.category = category;
//		this.price = price; 
//	
//	}

	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	
	
	
}