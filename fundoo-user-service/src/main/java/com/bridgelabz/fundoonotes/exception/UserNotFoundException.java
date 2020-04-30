package com.bridgelabz.fundoonotes.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String message, HttpStatus notFound) {
		super(message);
		// TODO Auto-generated constructor stub
	}




	
}
