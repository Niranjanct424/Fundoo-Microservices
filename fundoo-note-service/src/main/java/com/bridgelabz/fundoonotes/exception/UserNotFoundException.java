package com.bridgelabz.fundoonotes.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;
@Getter
public class UserNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus statusCode;

	public UserNotFoundException(String message ,HttpStatus statusCode) {
		
		this.message = message;
		this.statusCode = statusCode;
	}
}