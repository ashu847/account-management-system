package com.demo.service;

import java.util.List;

import com.demo.entity.Account;
import com.demo.entity.Transaction;
import com.demo.entity.User;

public interface AccountManagementServiceDAO {
	
	public String openAccount(User user);
	
	public String tranctionOccur(Transaction tr);
	
	public List<Account> accountList();
	
	public String accountUpdate(User user);
}
