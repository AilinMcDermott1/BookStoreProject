package com.example.BookStore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String surname;
	private String address;
	private String userName; 
	private String password;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	public Set<Book> books; 
	
	public User() {
		super(); 
	}
	
	public User(Long id, String firstName, String surname, String address, String userName, String password, Set<Book> books) {
		super(); 
		this.id = id; 
		this.firstName = firstName; 
		this.surname = surname; 
		this.address = address; 
		this.userName = userName; 
		this.password = password; 
		this.books = books; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	

}
