package com.cropdeal.userservice.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cropdeal.userservice.Exception.UserAlreadyExistsException;
import com.cropdeal.userservice.Exception.UserNotFoundException;
import com.cropdeal.userservice.models.User;
import com.cropdeal.userservice.repository.UserRepository;
import com.cropdeal.userservice.service.UserService;



@Service
public class UserServiceImpl implements UserService {

	public static Logger logFarmer = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepo;
	
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;


	@Override
	public User addUser(User user) throws UserAlreadyExistsException {

		if (this.userRepo.existsByUserName(user.getUserName())) {
			
			logFarmer.error("User already exist try new one");

			throw new UserAlreadyExistsException("UserName already exist try new one");
		}
		
		String encryptedPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPwd);
				
		return userRepo.save(user);

	}

	@Override
	public List<User> viewUser() throws UserNotFoundException {

		List<User> user = userRepo.findAll();
		if (user == null) {
			throw new UserNotFoundException("User not found");
		} else

			return user;
	}

	@Override
	public User viewUser(String id) throws UserNotFoundException {

		Optional<User> user = userRepo.findById(id);

		User u = null;
		if (user.isPresent()) {

			u = user.get();
		} else {
			throw new UserNotFoundException("No such User");
		}
		return u;
	}
	
	@Override
	public String deleteUser(String id) throws UserNotFoundException {
		
		String message = null;
		Optional<User> user=userRepo.findById(id);
		if (user.isPresent()) {
			userRepo.deleteById(id);
			message = "Deleted Successfully";
			logFarmer.info(message);
		}
		else {
			message = "User Not found";
			logFarmer.error(message);
		}
		return message;
	}

	@Override
	public User updateUser( User user) throws UserNotFoundException {
		Optional<User> u = userRepo.findById(user.getId());

		User userRecord = null;
		if (u.isPresent()) {
		  userRecord = u.get();
			userRepo.save(user);
		} else {
			logFarmer.error("User not found");
		}
		return userRecord;
	}

	

}
