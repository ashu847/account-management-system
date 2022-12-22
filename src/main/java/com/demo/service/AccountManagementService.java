package com.demo.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Account;
import com.demo.entity.FDAccount;
import com.demo.entity.SavingsAccount;
import com.demo.entity.Transaction;
import com.demo.entity.User;
import com.demo.repo.AccountRepository;
import com.demo.repo.FDAccountRepository;
import com.demo.repo.SavingsAccountRepository;
import com.demo.repo.TransactionRepository;
import com.demo.repo.UserRepository;



@Service
public class AccountManagementService implements AccountManagementServiceDAO {
	
	@Autowired
	AccountRepository accountRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SavingsAccountRepository savingsAccountRepo;
	
	@Autowired
	FDAccountRepository fdAccountRepo;
	
	@Autowired
	TransactionRepository trRepo;

	@Override
	public String openAccount(User user) {
		if(userRepo.checkNullUserId()==null) {
			user.setUserId(1);
		}
		else {
		user.setUserId(userRepo.getMaxUserId()+1);
		}
		userRepo.save(user);
		if(user.getAccountType().equals("Saving")) {
			Account acc = new SavingsAccount();  //Up-Casting
			SavingsAccount account= (SavingsAccount)acc; // Down-Casting
			if(accountRepo.checkNullAccountNo()==null) {
				account.setAccountNo(1001L);
			}
			else {
			account.setAccountNo(accountRepo.getMaxAccountNo()+1L);
			}
			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			account.setBalance(BigDecimal.valueOf(3000));
			account.setOpeningDate(date);
			account.setAccType(user.getAccountType());
			account.setUser(user);
			account.getInrestRate();
			accountRepo.save(account);
		
			return "Saving Account Opened Successfully";
			
		}
		else {
			Account acc = new FDAccount(); //Up-Casting
			FDAccount account = (FDAccount)acc; //Down-Casting
			if(accountRepo.checkNullAccountNo()==null) {
				account.setAccountNo(1001L);
			}
			else {
			account.setAccountNo(accountRepo.getMaxAccountNo()+1L);
			}
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		     String date= sdf.format(new Date());
			//account.setAccountNo(10002L);
			account.setBalance(BigDecimal.valueOf(3000));
			account.setAccType(user.getAccountType());
			account.setOpeningDate(date);
			account.setUser(user);
			account.getInrestRate();
			
	        Calendar cal = Calendar.getInstance();
	        try{  
	        	cal.setTime(sdf.parse(date)); 
	         }catch(ParseException e){  
	             e.printStackTrace();  
	          }  
	        
	        cal.add(Calendar.DAY_OF_YEAR, 3); 

			account.setMaturityDate(sdf.format(cal.getTime()));
			account.setAccountRenew(false);
			accountRepo.save(account);
			return "FD Account Opened Successfully";
		}

		
	}

	@Transactional
	@Override
	public String tranctionOccur(Transaction tr) {
		Optional<Account> fromAccount= accountRepo.findById(tr.getFromAccount());
		Optional<Account> toAccount= accountRepo.findById(tr.getToAccount());
		
		if(fromAccount.get().getAccType().equals("Saving") && toAccount.get().getAccType().equals("Saving")) {
		accountRepo.updateFromAccountBalance(tr.getFromAccount(), tr.getAmount());
		accountRepo.updateToAccountBalance(tr.getToAccount(), tr.getAmount());
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		tr.setTrDate(date);
		trRepo.save(tr);
		return "Tranaction Succesfull";
		}
		else {
			return "Transaction Not Possible in FD Account";
		}
	}

	@Override
	public List<Account> accountList() {

		return accountRepo.findAll();
	}

	@Override
	public String accountUpdate(User user) {
		userRepo.save(user);
		return "Account Updated Successfully";
	}
	
	
	

}
