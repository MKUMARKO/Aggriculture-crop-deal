package com.cropdeal.orderservice.Exception;

public class CropAlreadyBookedException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3007812387543407717L;
	

	public CropAlreadyBookedException(String message) {
		
		super (message);
	}
	

}
