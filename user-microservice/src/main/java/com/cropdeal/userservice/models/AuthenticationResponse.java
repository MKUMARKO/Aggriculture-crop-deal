package com.cropdeal.userservice.models;

public class AuthenticationResponse {
	private String response;

	public AuthenticationResponse(String response) {
		super();
		this.response = response;
	}

	public AuthenticationResponse() {

	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
