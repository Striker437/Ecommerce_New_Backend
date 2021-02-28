package com.Ecommerce.CustomException;

public class NoProductsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	public NoProductsException(String message) {
		
		this.message=message;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
	
	
	

}
