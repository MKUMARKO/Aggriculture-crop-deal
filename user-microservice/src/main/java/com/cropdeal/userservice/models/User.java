package com.cropdeal.userservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(description = "Registration form of the user")
@Document(collection = "user")
public class User {

	@Id
	@ApiModelProperty(notes = "This is unique id")
	private String id;

	@ApiModelProperty(notes = "Enter the farmer name")
	private String name;

	@ApiModelProperty(notes = "Enter the User name")
	private String userName;

	@ApiModelProperty(notes = "Enter the contact number")
	private String contactNo;

	@ApiModelProperty(notes = "Enter the address")
	private Location address;

	@ApiModelProperty(notes = "enter the password")
	private String password;

	@ApiModelProperty(notes = "enter the role")
	private String role;
	
	@ApiModelProperty(notes = "enter the bank details")
	private BankDetails bankDetails;
	
	@ApiModelProperty(notes = "is user active or not")
	private Boolean isActive;

	public User(String id, String name, String userName, String contactNo, Location address, String password,
			String role, BankDetails bankDetails, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.contactNo = contactNo;
		this.address = address;
		this.password = password;
		this.role = role;
		this.bankDetails = bankDetails;
		this.isActive = isActive;
	}

	public User() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userName=" + userName + ", contactNo=" + contactNo
				+ ", address=" + address + ", password=" + password + ", role=" + role + ", bankDetails=" + bankDetails
				+ ", isActive=" + isActive + "]";
	} 
	
	
	
	
	


}