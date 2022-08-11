package com.cropdeal.userservice.models;

public class AuthenticationJwtResponse {
	
	private final String jwt;

	
	public AuthenticationJwtResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}
	
	

}
