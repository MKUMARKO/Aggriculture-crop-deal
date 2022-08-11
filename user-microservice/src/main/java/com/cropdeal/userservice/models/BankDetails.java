package com.cropdeal.userservice.models;

import lombok.Data;

@Data
public class BankDetails {
	
	private String accountNo;
	private String ifsc;
	private String branchName;
	
	private String upi;

}
