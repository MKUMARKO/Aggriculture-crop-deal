package com.cropdeal.orderservice.Exceptionhandler;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cropdeal.orderservice.Exception.CropAlreadyBookedException;
import com.cropdeal.orderservice.Exception.OrderNotFoundException;


@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { OrderNotFoundException.class })
	
		    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		
		        String bodyOfResponse = "order not found";
		        return handleExceptionInternal(ex, bodyOfResponse, 
		          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		    }
	
	@ExceptionHandler(value = { CropAlreadyBookedException.class })
	
    protected ResponseEntity<Object> handleConflict1 (RuntimeException ex, WebRequest request) {

        String bodyOfResponse = "Crop already booked";
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
	
	
}