package com.cropdeal.userservice.resource;

import java.util.Arrays;
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
import org.springframework.web.client.RestTemplate;

import com.cropdeal.userservice.Exception.UserAlreadyExistsException;
import com.cropdeal.userservice.Exception.UserNotFoundException;
import com.cropdeal.userservice.models.Crop;
import com.cropdeal.userservice.models.Order;
import com.cropdeal.userservice.models.User;
import com.cropdeal.userservice.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/farmer")
public class FarmerController {

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;
    
	
	@GetMapping("/finduser/{id}")
	public User getUser(@PathVariable String id) throws UserNotFoundException {
		return userService.viewUser(id);
	}


	@PutMapping("/updateuser")
	@ApiOperation(value = "update the farmer")
	public User updateUser(@RequestBody User user) throws UserNotFoundException {
		return userService.updateUser(user);

	}

	// ========Intercommunication with crop ============

	// Add crop

	@PostMapping("/addCrop")
	@ApiOperation(value = "Add the crop")
	public Crop addCrop(@RequestBody Crop crop) {
		Crop crp = restTemplate.postForObject("http://CROP-SERVICE/crop/addCrop", crop, Crop.class);
		return crp;
	}

	// Update crop

	@PutMapping("/updateCrop")
	@ApiOperation(value = "Update the crop")
	public Crop updateCrop(@RequestBody Crop crop) {
		restTemplate.put("http://CROP-SERVICE/crop/updateCrop", crop, Crop.class);
		return crop;
	}
	
	// Delete the crop
	
	@DeleteMapping("/deleteCrop/{id}")
	@ApiOperation(value = "Delete the crop")
	public void deleteCrop(@PathVariable("id") String id) {
	
		restTemplate.delete("http://CROP-SERVICE/crop/deleteCrop/" + id);
		
	}

	// view crop by crop id

	@GetMapping("/viewCropById/{id}")
	@ApiOperation(value = "Get crop by crop id")
	public Crop getCrop(@PathVariable String id) {
		return restTemplate.getForObject("http://CROP-SERVICE/crop/viewCropById/" + id, Crop.class);
	}


	// view all crops under the individual farmer

	@GetMapping("/viewAllCropByFarmer/{farmerId}")
	@ApiOperation(value = "Get all crops under the farmer")
	public List<Crop> getAllCropsByFarmer(@PathVariable String farmerId) {
		return Arrays.asList(
				restTemplate.getForObject("http://CROP-SERVICE/crop/viewAllCropByFarmer/" + farmerId, Crop[].class));

	}
	
	//>>>>>>>>>>> intercommunication with order
	
	// view all orders under farmer

		@GetMapping("/viewAllOrderByFarmer/{farmerId}")
		@ApiOperation(value = "Get all orders under the farmer ")
		public List<Order> getAllOrderByFarmer(@PathVariable String farmerId) {
			return Arrays.asList(restTemplate.getForObject("http://ORDER-SERVICE/order/viewAllOrderByFarmer/" + farmerId,
					Order[].class));

		}

}
