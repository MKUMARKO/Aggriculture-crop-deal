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
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;

	// -------Curd Operations for admin -----------
	
	
	/*
	
	  @PostMapping("/adduser")
	  
	  @ApiOperation(value = "Register the Admin") public User addUser(@RequestBody
	  User user) throws UserAlreadyExistsException { return
	  userService.addUser(user);
	  
	  }
	  
	  @PutMapping("/updateuser") public User updateUser(@RequestBody User user)
	  throws UserNotFoundException { return userService.updateUser(user);
	  
	  }
	  
	  */
	 

	@GetMapping("/finduser")
	public List<User> getUser() throws UserNotFoundException {
		return userService.viewUser();
	}

	@GetMapping("/finduser/{id}")
	public User getUser(@PathVariable String id) throws UserNotFoundException {
		return userService.viewUser(id);
	}

	@DeleteMapping("/deleteuser/{id}")
	public String deleteUser(
			@ApiParam(value = "id of the farmer which has to be delete", required = true) @PathVariable String id)
			throws UserNotFoundException {
		return userService.deleteUser(id);

	}

	  /* >>>>>>>>      COMMUNICATION WITH CROPS      <<<<<<<<<<< */

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

	// View all crops

	@GetMapping("/viewAllCrop")
	@ApiOperation(value = "Get all crop")
	public List<Crop> getAllCrop() {
		return Arrays.asList(restTemplate.getForObject("http://CROP-SERVICE/crop/viewAllCrop", Crop[].class));

	}

	 /* >>>>>>>>      COMMUNICATION WITH ORDERS     <<<<<<<<<<< */

	// view order by order id

	@GetMapping("/viewOrderById/{id}")
	@ApiOperation(value = "Get order by order id")
	public Order getOrder(@PathVariable String id) {
		return restTemplate.getForObject("http://ORDER-SERVICE/order/viewOrderById/" + id, Order.class);
	}

	// View all orders

	@GetMapping("/viewAllOrder")
	@ApiOperation(value = "Get all order")
	public List<Order> getAllOrder() {
		return Arrays.asList(restTemplate.getForObject("http://ORDER-SERVICE/order/viewAllOrder", Order[].class));

	}

	// view all orders under dealer

	@GetMapping("/viewAllOrderByDealer/{dealerId}")
	@ApiOperation(value = "Get all orders under the dealer")
	public List<Order> getAllOrderByDealer(@PathVariable String dealerId) {
		return Arrays.asList(restTemplate.getForObject("http://ORDER-SERVICE/order/viewAllOrderByDealer/" + dealerId,
				Order[].class));

	}

	// view all orders under farmer

	@GetMapping("/viewAllOrderByFarmer/{farmerId}")
	@ApiOperation(value = "Get all orders under the farmer ")
	public List<Order> getAllOrderByFarmer(@PathVariable String farmerId) {
		return Arrays.asList(restTemplate.getForObject("http://ORDER-SERVICE/order/viewAllOrderByFarmer/" + farmerId,
				Order[].class));

	}

}