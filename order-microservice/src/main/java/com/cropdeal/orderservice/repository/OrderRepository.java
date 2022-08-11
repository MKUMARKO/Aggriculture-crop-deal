package com.cropdeal.orderservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cropdeal.orderservice.models.Order;


public interface OrderRepository extends MongoRepository<Order, String> {
	
	List<Order> findByFarmerId(String farmerId);

	List<Order> findByDealerId(String dealerId);

	boolean existsByCropId(String cropId);
} 