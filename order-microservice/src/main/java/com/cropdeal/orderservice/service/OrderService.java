package com.cropdeal.orderservice.service;

import java.util.List;

import com.cropdeal.orderservice.Exception.CropAlreadyBookedException;
import com.cropdeal.orderservice.Exception.OrderNotFoundException;
import com.cropdeal.orderservice.models.Order;


public interface OrderService {
	
	
	public Order addOrder(Order order) throws CropAlreadyBookedException;
	
	public String deleteOrder(String orderId) throws OrderNotFoundException;
	
	public Order viewOrderByOrderId(String orderId) throws OrderNotFoundException;
	
	public List<Order> viewAllOrder() throws OrderNotFoundException;
	
	public List<Order> viewAllOrderByFarmerId(String farmerId) throws OrderNotFoundException;
	
	public List<Order> viewAllOrderByDealerId(String dealerId) throws OrderNotFoundException;

}
