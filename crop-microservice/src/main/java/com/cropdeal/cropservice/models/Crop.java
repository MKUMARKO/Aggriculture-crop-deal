package com.cropdeal.cropservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "crop")
public class Crop {

	
	@Id
	private String id;
	private String cropName;
	private String cropLocation;
	
	private double cropQty;
	private String cropType;
	private String cropImgUrl;
	private String cropDesc;
	private String cropPrice;
	
	private String farmerId;
	private String uploadedBy;

}