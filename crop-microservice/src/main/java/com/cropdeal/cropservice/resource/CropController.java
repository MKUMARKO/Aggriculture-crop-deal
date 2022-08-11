package com.cropdeal.cropservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cropdeal.cropservice.Exception.CropNotFoundException;
import com.cropdeal.cropservice.models.Crop;
import com.cropdeal.cropservice.service.CropService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/crop")
public class CropController {

	@Autowired
	private CropService cropService;

	// -----Curd Operations for farmer ----------

	// Adding the crop

	@PostMapping("/addCrop")
	@ApiOperation(value = "Add the crop")
	public Crop addCrop(@RequestBody Crop crop) {
		return cropService.addCrop(crop);

	}

	// view all the crops

	@GetMapping("/viewAllCrop")
	public List<Crop> getAllCrop() throws CropNotFoundException {
		return cropService.viewAllCrop();
	}

	// view crop by crop id

	@GetMapping("/viewCropById/{id}")
	public Crop getCrop(@PathVariable String id) throws CropNotFoundException {
		return cropService.viewCrop(id);
	}

	// Delete crop

	@DeleteMapping("/deleteCrop/{id}")
	public String deleteCrop(
			@ApiParam(value = "id of the Crop which has to be delete", required = true) @PathVariable String id)
			throws CropNotFoundException {
		return cropService.deleteCrop(id);

	}

	// view all the crops by farmer

	@GetMapping("/viewAllCropByFarmer/{farmerId}")
	public List<Crop> getAllCropByFarmer(@PathVariable String farmerId) throws CropNotFoundException {
		return cropService.viewAllCropByFarmer(farmerId);
	}

	// Update the crop

	@PutMapping("updateCrop")
	public Crop updateCrop(@RequestBody Crop crop) throws CropNotFoundException {
		return cropService.updateCrop(crop);

	}

}