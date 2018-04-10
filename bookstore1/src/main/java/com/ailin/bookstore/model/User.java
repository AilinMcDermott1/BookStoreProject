package com.ailin.bookstore.model;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private Set<Role> roles;
    private List<Book> shoppingCart;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
	public void saveBookToCart(Book book) {
		shoppingCart.add(book);
	}
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name = "user_books", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
	public List<Book> getShoppingCart(){
		return shoppingCart;
	}
	
	public void setShoppingCart(List<Book> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public void removeBookFromShoppingCart(Book book) {
		this.shoppingCart = shoppingCart;
		
	}
}
