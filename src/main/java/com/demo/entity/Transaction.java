package com.demo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TRANSACTION")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TRANSACTIONID")
	private int transactionId;
	
	@Column(name="TOACCOUNT")
	private long toAccount;
	
	@Column(name="FROMACCOUNT")
	private long fromAccount;
	
	@Column(name="AMOUNT")
	private BigDecimal amount;
	
	@Column(name="TRDATE")
	private String trDate;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(int transactionId, long toAccount, long fromAccount, BigDecimal amount, String trDate) {
		this.transactionId = transactionId;
		this.toAccount = toAccount;
		this.fromAccount = fromAccount;
		this.amount = amount;
		this.trDate = trDate;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public long getToAccount() {
		return toAccount;
	}

	public void setToAccount(long toAccount) {
		this.toAccount = toAccount;
	}

	public long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTrDate() {
		return trDate;
	}

	public void setTrDate(String trDate) {
		this.trDate = trDate;
	}
	
	

}
