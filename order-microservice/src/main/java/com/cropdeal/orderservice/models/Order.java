package com.cropdeal.orderservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data

@Document("order")
public class Order {
	
	@Id
	private String orderId;
	
	private String dealerId;
	
	private String farmerId;
	
	private String cropId;
	
	private String dealerName;
	
	private String farmerName;
	
	private String cropName;
	
	private String dealerMobile;
	
	private String farmerMobile;
	
	private String orderStatus;


}
