package com.demo.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="FDACCOUNT")
public class FDAccount extends Account{

	@Column(name="AUTORENEW")
	boolean accountRenew;
	
	@Column(name="MATURITYDATE")
	Date maturityDate;
	
	@Column(name="INTERESTEARNERD")
	BigDecimal interestEarned;
	
	

	public FDAccount() {
		super();
	}

	public FDAccount(long accountNo, BigDecimal balance, Date openingDate,String accType, BigDecimal interestEarned,Date maturityDate, boolean accountRenew) {
		super(accountNo, balance, openingDate, accType);
		this.interestEarned=interestEarned;
		this.accountRenew=accountRenew;
		this.maturityDate=maturityDate;
	}

	@Override
	public BigDecimal getInterestRate() {
		final double interestRate= 0.04;
        interestEarned= balance.multiply(BigDecimal.valueOf(interestRate*365)).divide(BigDecimal.valueOf(100.0),RoundingMode.HALF_UP);
               return interestEarned;
        
	}

	public boolean isAccountRenew() {
		return accountRenew;
	}

	public void setAccountRenew(boolean accountRenew) {
		this.accountRenew = accountRenew;
	}

	

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	public BigDecimal getInterestEarned() {
		return interestEarned;
	}

	public void setInterestEarned(BigDecimal interestEarned) {
		this.interestEarned = interestEarned;
	}

	

}
