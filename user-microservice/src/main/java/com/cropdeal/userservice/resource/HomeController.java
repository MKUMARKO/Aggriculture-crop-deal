package com.cropdeal.userservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cropdeal.userservice.Exception.UserAlreadyExistsException;
import com.cropdeal.userservice.jwtutil.JwtUtil;
import com.cropdeal.userservice.models.AuthenticationJwtResponse;
import com.cropdeal.userservice.models.AuthenticationRequest;
import com.cropdeal.userservice.models.AuthenticationResponse;
import com.cropdeal.userservice.models.Crop;
import com.cropdeal.userservice.models.User;
import com.cropdeal.userservice.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	
	// user registration form 
	
	@PostMapping("/adduser")
	@ApiOperation(value = "Register the dealer")
	public User addUser(@RequestBody User user) throws UserAlreadyExistsException {
		return userService.addUser(user);

	}
	
	
	// >>>login form
	@PostMapping("/login")
	private ResponseEntity<?> authenticateClient (@RequestBody AuthenticationRequest authenticationRequest){
		
		String userName = authenticationRequest.getUserName();
		
		String password = authenticationRequest.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		}
		
		catch (Exception e) {
			return ResponseEntity.ok(new AuthenticationResponse("Error during client Authentication "+ userName));
			
		}
		
		//return ResponseEntity.ok(new AuthenticationResponse("Successful Authentication "+ userName));
		
		
		final UserDetails userDetails =  userDetailsService.loadUserByUsername(userName);
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationJwtResponse(jwt));
		}
	
	
	
	//>>>>>>> communication with crops
	
	// View all crops

			@GetMapping("/viewAllCrop")
			@ApiOperation(value = "Get all crop")
			public List<Crop> getAllCrop() {
				return Arrays.asList(restTemplate.getForObject("http://CROP-SERVICE/crop/viewAllCrop", Crop[].class));

			}
			

}
