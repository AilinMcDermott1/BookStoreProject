package com.ailin.bookstore.model;

import com.ailin.bookstore.model.User;


public interface UserBuilder {
	 void buildUsername();
	    void buildPassword();
	    void buildPasswordConfirm();
	    void buildShipping_details();
	    void buildPayment_details();
	    User getUser();

}
