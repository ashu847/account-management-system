package com.demo.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	String maturityDate;
	
	@Column(name="INTERESTEARNERD")
	BigDecimal interestEarned;
	
	

	public FDAccount() {
		super();
	}

	public FDAccount(long accountNo, BigDecimal balance, String openingDate,String accType, BigDecimal interestEarned,String maturityDate, boolean accountRenew) {
		super(accountNo, balance, openingDate, accType);
		this.interestEarned=interestEarned;
		this.accountRenew=accountRenew;
		this.maturityDate=maturityDate;
	}

	@Override
	public BigDecimal getInrestRate() {
		final double interestRate= 0.04;
        interestEarned= balance.multiply(BigDecimal.valueOf(interestRate*365)).divide(BigDecimal.valueOf(100.0),RoundingMode.HALF_UP);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date= sdf.format(new Date());
        Calendar cal = Calendar.getInstance();
        try{  
        	cal.setTime(sdf.parse(date)); 
         }catch(ParseException e){  
             e.printStackTrace();  
          }  
        
        cal.add(Calendar.DAY_OF_YEAR, 3);  
        maturityDate=	sdf.format(cal.getTime());
        accountRenew=false;
        return interestEarned;
        
	}

	public boolean isAccountRenew() {
		return accountRenew;
	}

	public void setAccountRenew(boolean accountRenew) {
		this.accountRenew = accountRenew;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public BigDecimal getInterestEarned() {
		return interestEarned;
	}

	public void setInterestEarned(BigDecimal interestEarned) {
		this.interestEarned = interestEarned;
	}

	

}
