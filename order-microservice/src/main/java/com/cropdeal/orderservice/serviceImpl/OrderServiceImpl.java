package com.cropdeal.orderservice.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cropdeal.orderservice.Exception.CropAlreadyBookedException;
import com.cropdeal.orderservice.Exception.OrderNotFoundException;
import com.cropdeal.orderservice.models.Order;
import com.cropdeal.orderservice.repository.OrderRepository;
import com.cropdeal.orderservice.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

	public static Logger logOrder = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepo;

	@Override
	public Order addOrder(Order order) throws CropAlreadyBookedException{
		
          if (this.orderRepo.existsByCropId(order.getCropId())) {
			
			logOrder.error("crop already booked");

			throw new CropAlreadyBookedException ("crop already booked");
		}
		return orderRepo.save(order);
	}

	@Override
	public List<Order> viewAllOrder() throws OrderNotFoundException {

		List<Order> order = orderRepo.findAll();
		if (order == null) {
			throw new OrderNotFoundException("Order not found");
		} else

			return order;
	}

	@Override
	public Order viewOrderByOrderId(String orderId) throws OrderNotFoundException {

		Optional<Order> order = orderRepo.findById(orderId);

		Order farm = null;
		if (order.isPresent()) {

			farm = order.get();
		} else {
			throw new OrderNotFoundException("No orders found");
		}
		return farm;
	}

	@Override
	public List<Order> viewAllOrderByFarmerId(String farmerId) throws OrderNotFoundException {

		List<Order> order = orderRepo.findByFarmerId(farmerId);

		if (order == null) {
			throw new OrderNotFoundException("order not found");
		} else
			return order;
	}

	@Override
	public List<Order> viewAllOrderByDealerId(String dealerId) throws OrderNotFoundException {

		List<Order> order = orderRepo.findByDealerId(dealerId);

		if (order ==null) {
			throw new OrderNotFoundException("order not found");
		}
		else
			return order;
	}

	@Override
	public String deleteOrder(String orderId) throws OrderNotFoundException {
		
		String message = null;
		Optional<Order> order = orderRepo.findById(orderId);
		if (order.isPresent()) {
			orderRepo.deleteById(orderId);
			message = "Deleted Successfully";
			logOrder.info(message);
		}
		else {
			message = "order Not found";
			logOrder.error(message);
		}
		return message;
	}

}
