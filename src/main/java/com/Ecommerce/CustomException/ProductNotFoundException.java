package com.Ecommerce.CustomException;

public class ProductNotFoundException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	public ProductNotFoundException(String message) {
		this.message=message;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
