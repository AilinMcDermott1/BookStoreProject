package com.ailin.bookstore.prototype;

public class CloneFactory {
	
	public UserClone getClone(UserClone userClone) {
		return userClone.makeCopy(); 
	}

}
