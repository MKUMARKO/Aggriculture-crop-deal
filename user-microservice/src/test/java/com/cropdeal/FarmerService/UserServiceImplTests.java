package com.cropdeal.FarmerService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cropdeal.userservice.models.User;
import com.cropdeal.userservice.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTests {

	
	@MockBean
	private UserRepository userRepo;
	
	@Test
	public void addfarmerTest() {
		User user = new User("999", "def", "def@33", "234567", null);
		when(farmerrepo.save(user)).thenReturn(user);
		assertEquals(user, service.addfarmer(user));
	}

	
	
	@Test
	public void getFarmerTest() {
		when(farmerrepo.findAll()).thenReturn(Stream.of(new User("123", "abcd", "abc@123", "34567889", null),
				new User("321", "cba", "cba@321", "34567889", null)).collect(Collectors.toList()));
		assertEquals(2, service.getfarmer().size());
	}

	

	@Test
	public void deleteFarmerbyidTest() {
		String farmer = Farmer("999", "def", "def@33", "234567", null);
		service.deleteFarmer(farmer);
		verify(farmerrepo, times(1)).deleteById(farmer);
	}

	private String Farmer(String string, String string2, String string3, String
	  string4, Object object) { // TODO Auto-generated method stub return null; }

	@Test
	public void getFarmerbyIdTest() {
		String id = "2";
		service.getfarmer(id);
		verify(farmerrepo).findById(id);
	}

}
