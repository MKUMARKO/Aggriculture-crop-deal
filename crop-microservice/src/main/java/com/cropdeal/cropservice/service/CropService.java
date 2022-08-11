package com.cropdeal.cropservice.service;

import java.util.List;

import com.cropdeal.cropservice.Exception.CropNotFoundException;
import com.cropdeal.cropservice.models.Crop;

public interface CropService {

	public Crop addCrop(Crop crop);

	public String deleteCrop(String id) throws CropNotFoundException;

	public Crop updateCrop(Crop crop) throws CropNotFoundException;

	public Crop viewCrop(String id) throws CropNotFoundException;

	//public Crop viewCropByFarmer(String farmerId) throws CropNotFoundException;
	
	public List<Crop> viewAllCrop() throws CropNotFoundException;
    
	public List<Crop> viewAllCropByFarmer(String farmerId) throws CropNotFoundException;

}
