package com.cropdeal.userservice.models;

import lombok.Data;

@Data
public class Location {
	private String street;
	private String village;
	private String city;
	private String state;
	private String pincode;

	
}