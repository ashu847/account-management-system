package com.demo.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SAVINGSACCOUNT")
public class SavingsAccount extends Account{
	
	@Column(name="INTERESTEARNED")
	BigDecimal interestEarned;

	public SavingsAccount() {
		super();
	}

	public SavingsAccount(long accountNo, BigDecimal balance, String openingDate,String accType, BigDecimal interestEarned) {
		super(accountNo, balance, openingDate, accType);
		this.interestEarned=interestEarned;
	}

	@Override
	public BigDecimal getInrestRate() {
		final double interestRate= 0.04;
		interestEarned= balance.multiply(BigDecimal.valueOf(interestRate*30)).divide(BigDecimal.valueOf(365.0),RoundingMode.HALF_UP);
        return interestEarned;
	}

	public BigDecimal getInterestEarned() {
		return interestEarned;
	}

	public void setInterestEarned(BigDecimal interestEarned) {
		this.interestEarned = interestEarned;
	}

	
}
