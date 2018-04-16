package com.ailin.bookstore.model;

import java.util.List;
import java.util.Set;

//import javax.persistence.Column;
//import javax.persistence.ElementCollection;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinTable;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
import javax.persistence.*;

@Entity
public class PurchaseHistory {
    private Long id;
    private String username;
    private String password;
    private String passwordConfirm;
    private String shipping_address;
    private String payment_details;
    private Set<Role> roles;
    private List<String> purchaseHistory;


    public PurchaseHistory() {
  		super();
  	}
     
public PurchaseHistory(Long id, String username, String password, Set<Role> roles) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.roles = roles;
}

public PurchaseHistory(Long id, String username, String password, String passwordConfirm) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.passwordConfirm = passwordConfirm;
}

public PurchaseHistory(Long id, String username, String shipping_address, List<String> purchaseHistory) {
	super();
	this.id = id;
	this.username = username;
	this.shipping_address = shipping_address;
	this.purchaseHistory = purchaseHistory;
}

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

public String getPasswordConfirm() {
    return passwordConfirm;
}

public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
}

public String getShipping_address() {
	return shipping_address;
}

public void setShipping_address(String shipping_address) {
	this.shipping_address = shipping_address;
}

public String getPayment_details() {
	return payment_details;
}

public void setPayment_details(String payment_details) {
	this.payment_details = payment_details;
}

@ManyToMany
@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
public Set<Role> getRoles() {
    return roles;
}

public void setRoles(Set<Role> roles) {
    this.roles = roles;
}


public void setPurchaseHistory(List<String> purchaseHistory) {
	this.purchaseHistory = purchaseHistory;
}

@Column
@ElementCollection(targetClass=String.class)
public List<String> getPurchaseHistory() {
	return purchaseHistory;
}


}
