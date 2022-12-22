package com.demo.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
	
	@Id
	@Column(name="ACCOUNTNO")
	long accountNo;
	
	@Column(name="BALANCE")
	BigDecimal balance;
	
	@Column(name="OPENINGDATE")
	String openingDate;
	
	@Column(name="ACCOUNTTYPE")
	String accType;
	
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    User user;

	public Account() {

	}
    



	public Account(long accountNo, BigDecimal balance, String openingDate, String accType) {
		this.accountNo = accountNo;
		this.balance = balance;
		this.openingDate = openingDate;
		this.accType = accType;
	}







	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}



	public String getOpeningDate() {
		return openingDate;
	}



	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getAccType() {
		return accType;
	}



	public void setAccType(String accType) {
		this.accType = accType;
	}




	public abstract BigDecimal getInrestRate();
	

}
