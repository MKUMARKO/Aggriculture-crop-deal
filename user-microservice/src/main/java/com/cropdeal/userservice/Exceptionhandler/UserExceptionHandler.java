package com.cropdeal.userservice.Exceptionhandler;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cropdeal.userservice.Exception.UserAlreadyExistsException;
import com.cropdeal.userservice.Exception.UserNotFoundException;


@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { UserNotFoundException.class })
	
		    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		
		        String bodyOfResponse = "user not found";
		        return handleExceptionInternal(ex, bodyOfResponse, 
		          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		    }
	
	@ExceptionHandler(value = { UserAlreadyExistsException.class })
	
	protected ResponseEntity<Object> handleConflict2(RuntimeException ex, WebRequest request) {
		
		String bodyOfResponse = "UserName is already taken.Try new one";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
}