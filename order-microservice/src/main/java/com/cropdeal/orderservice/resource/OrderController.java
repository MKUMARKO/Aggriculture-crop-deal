package com.cropdeal.orderservice.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cropdeal.orderservice.Exception.CropAlreadyBookedException;
import com.cropdeal.orderservice.Exception.OrderNotFoundException;
import com.cropdeal.orderservice.models.Order;
import com.cropdeal.orderservice.service.OrderService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	// ------Curd Operations for ordes------
	
	
	
	//Add order
	
	@PostMapping("/addOrder")
	@ApiOperation(value = "order the crop")
	public Order addOrder(@RequestBody Order order) throws CropAlreadyBookedException {
		return orderService.addOrder(order);

	}
	
	//delete order
	
	@DeleteMapping("/deleteOrder/{orderId}")
	@ApiOperation(value = "cancle the order ")
	public String deleteUser(@ApiParam(value="id of the order which has to be delete" , required=true) @PathVariable String orderId) throws OrderNotFoundException {
		return orderService.deleteOrder(orderId);
	
	}
	
    // get all orders
	
	@GetMapping("/viewAllOrder")
	@ApiOperation(value = "find all  the order")
	public List<Order> getAllOrder() throws OrderNotFoundException {
		return orderService.viewAllOrder();
	}
    
	//get order by order id
	
	@GetMapping("/viewOrderById/{orderId}")
	@ApiOperation(value = "find the order by the Order id ")
	public Order getOrderByOrderId(@PathVariable String orderId) throws OrderNotFoundException {
		return orderService.viewOrderByOrderId(orderId);
	}
    
	//get all orders by farmer id
	
	@GetMapping("/viewAllOrderByFarmer/{farmerId}")
	@ApiOperation(value = "find the orders by the farmer id ")
	public List<Order> getOrderByFarmerId(@PathVariable String farmerId) throws OrderNotFoundException {
		return orderService.viewAllOrderByFarmerId(farmerId);
	}
    
	//get  all orders by dealer id
	
	@GetMapping("/viewAllOrderByDealer/{dealerId}")
	@ApiOperation(value = "find the orders by the dealer id ")
	public List<Order> getOrderByDealerId(@PathVariable String dealerId) throws OrderNotFoundException {
		return orderService.viewAllOrderByDealerId(dealerId);
	}

}