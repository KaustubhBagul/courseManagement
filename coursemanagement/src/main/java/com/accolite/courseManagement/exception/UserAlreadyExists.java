package com.accolite.courseManagement.exception;

public class UserAlreadyExists extends RuntimeException{

	private static final long serialVersionUID = 3L;
	
	public UserAlreadyExists(String message) {
		super(message);
	}

}
