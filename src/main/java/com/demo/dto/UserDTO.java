package com.demo.dto;

import java.math.BigDecimal;

import com.demo.entity.User;

public class UserDTO extends User{
	
	private BigDecimal balance;
	private String accountType;
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	

}
