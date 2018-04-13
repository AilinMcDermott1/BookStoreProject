package com.ailin.bookstore.prototype;

public class CloneFactory {
	
	public UserClone getClone(UserClone userSample) {
		return userSample.makeCopy(); 
	}

}
