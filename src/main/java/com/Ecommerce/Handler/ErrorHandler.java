package com.Ecommerce.Handler;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.Ecommerce.CustomException.ProductNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorHandler {
	
	

	
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler
	public ExceptionResponse ExceptionHandler(ProductNotFoundException exception,HttpServletRequest request)
	{
		ExceptionResponse errorResponse=new ExceptionResponse();
		errorResponse.setErrorMessage(exception.getMessage());
		errorResponse.setRequestedURI(request.getRequestURI());
		return errorResponse;
		
		
	}
	
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ExceptionResponse ExceptionHandler(NullPointerException exception,HttpServletRequest request)
	{
		ExceptionResponse errorResponse=new ExceptionResponse();
		errorResponse.setErrorMessage("null pointer exception");
		errorResponse.setRequestedURI(request.getRequestURI());
		return errorResponse;
		
	}
	
	
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ExceptionResponse ExceptionHandler(NumberFormatException exception,HttpServletRequest request)
	{
		ExceptionResponse errorResponse=new ExceptionResponse();
		errorResponse.setErrorMessage("Number Format exception");
		errorResponse.setRequestedURI(request.getRequestURI());
		return errorResponse;
		
	}

}
