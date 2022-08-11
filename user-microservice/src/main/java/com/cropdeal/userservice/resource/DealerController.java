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
@RequestMapping("/dealer")
public class DealerController {

	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;
    
	
	@GetMapping("/finduser/{id}")
	public User getUser(@PathVariable String id) throws UserNotFoundException {
		return userService.viewUser(id);
	}


	@PutMapping("/updateuser")
	@ApiOperation(value = "update the dealer")
	public User updateUser(@RequestBody User user) throws UserNotFoundException {
		return userService.updateUser(user);

	}

	// -------inter communication with order--------

	// Add order

	@PostMapping("/addOrder")
	@ApiOperation(value = "Add the order")
	public Order addOrder(@RequestBody Order order) {
		Order ord = restTemplate.postForObject("http://ORDER-SERVICE/order/addOrder", order, Order.class);
		return ord;
	}

	// Delete the order

	@DeleteMapping("/deleteOrder/{id}")
	@ApiOperation(value = "Delete the order")
	public void deleteOrder(@PathVariable("id") String id) {

		restTemplate.delete("http://ORDER-SERVICE/order/deleteOrder/" + id);

	}

	// view all orders under dealer

	@GetMapping("/viewAllOrderByDealer/{dealerId}")
	@ApiOperation(value = "Get all orders under the dealer")
	public List<Order> getAllOrderByDealer(@PathVariable String dealerId) {
		return Arrays.asList(restTemplate.getForObject("http://ORDER-SERVICE/order/viewAllOrderByDealer/" + dealerId,
				Order[].class));

	}
	
	//>>>>>>> communication with order
	
	// view crop by crop id

		@GetMapping("/viewCropById/{id}")
		@ApiOperation(value = "Get crop by crop id")
		public Crop getCrop(@PathVariable String id) {
			return restTemplate.getForObject("http://CROP-SERVICE/crop/viewCropById/" + id, Crop.class);
		}

		


}
