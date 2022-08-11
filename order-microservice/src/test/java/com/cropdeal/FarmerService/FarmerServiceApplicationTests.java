package com.cropdeal.FarmerService;



import static org.mockito.Mockito.times;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cropdeal.orderservice.models.Order;
import com.cropdeal.orderservice.repository.OrderRepository;
import com.cropdeal.orderservice.resource.OrderController;


@RunWith(SpringRunner.class)
@SpringBootTest
class FarmerServiceApplicationTests {

	
	@Autowired
	private OrderController service;
	

	@MockBean
	private OrderRepository farmerrepo;
	@Test
	public void getFarmerTest() {
		when(farmerrepo.findAll()).thenReturn(Stream
				.of(new Order("123","abcd","abc@123","34567889", null),new Order("321","cba","cba@321","34567889", null)).collect(Collectors.toList()));
		assertEquals(2, service.getfarmer().size());
	}
	

	@Test
	public void addfarmerTest() {
		Order order = new Order("999", "def","def@33", "234567",null);
		when(farmerrepo.save(order)).thenReturn(order);
		assertEquals(order, service.addfarmer(order));
	}

	@Test
	public void deleteFarmerbyidTest() {
		String farmer = Farmer("999", "def","def@33", "234567",null);
		service.deleteFarmer(farmer);
		verify(farmerrepo, times(1)).deleteById(farmer);
	}

	private String Farmer(String string, String string2, String string3, String string4, Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Test public void getFarmerbyIdTest() { 
	String id="2";
	service.getfarmer(id);
    verify(farmerrepo).findById(id);
	}


}
