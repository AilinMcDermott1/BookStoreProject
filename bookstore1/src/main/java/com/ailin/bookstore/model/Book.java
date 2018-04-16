package com.ailin.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	private String author;
	private String title;
	private String genre;
	private double price;
	private String image;


	
	
	public Book() {
		super();
	}



	
	public Book(Long id, String author, String title, String genre, double price, String image) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.price = price;
		this.image = image; 
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantity() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setQuantity(int newQuantity) {
		// TODO Auto-generated method stub
		
	}
	
	

}
