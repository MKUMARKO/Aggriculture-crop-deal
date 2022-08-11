package com.cropdeal.userservice.service;

import java.util.List;

import com.cropdeal.userservice.Exception.UserAlreadyExistsException;
import com.cropdeal.userservice.Exception.UserNotFoundException;
import com.cropdeal.userservice.models.User;


public interface UserService {
	
	
	public User addUser(User user) throws UserAlreadyExistsException;
	
	public List<User> viewUser() throws UserNotFoundException;
	
	public User viewUser(String id) throws UserNotFoundException;
	
	public String deleteUser(String id) throws UserNotFoundException;
	
	public User updateUser(User user)  throws UserNotFoundException;
	
}
