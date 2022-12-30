package com.demo.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.UserDTO;
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
	public String openAccount(UserDTO userDTO) {
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setAddress(userDTO.getAddress());
		user.setAadharNo(userDTO.getAadharNo());
		user.setPanCard(userDTO.getPanCard());
		user.setPhoneNo(userDTO.getPhoneNo());

		

		/* User use= userRepo.save(user); */

		if (userDTO.getAccountType().equals("Saving")) {
			Account acc = new SavingsAccount(); // Up-Casting
			SavingsAccount account = (SavingsAccount) acc; // Down-Casting
			if (accountRepo.checkNullAccountNo() == null) {
				account.setAccountNo(1001L);
			} else {
				account.setAccountNo(accountRepo.getMaxAccountNo() + 1L);
			}
			Date date = new Date();
			account.setBalance(userDTO.getBalance());
			account.setOpeningDate(date);
			account.setAccType(userDTO.getAccountType());
			account.setUser(user);
			account.getInterestRate();
			accountRepo.save(account);

			return "Saving Account Opened Successfully";

		} else {
			Account acc = new FDAccount(); // Up-Casting
			FDAccount account = (FDAccount) acc; // Down-Casting
			if (accountRepo.checkNullAccountNo() == null) {
				account.setAccountNo(1001L);
			} else {
				account.setAccountNo(accountRepo.getMaxAccountNo() + 1L);
			}
			Date date = new Date();
			account.setBalance(userDTO.getBalance());
			account.setOpeningDate(date);
			account.setAccType(userDTO.getAccountType());
			account.setUser(user);
			account.getInterestRate();

			Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.add(Calendar.YEAR, 3);
			account.setMaturityDate(calender.getTime());
			account.setAccountRenew(false);
			accountRepo.save(account);
			return "FD Account Opened Successfully";
		}

	}

	@Transactional
	@Override
	public String tranctionOccur(Transaction tr) {
		Optional<Account> fromAccount = accountRepo.findById(tr.getFromAccount());
		Optional<Account> toAccount = accountRepo.findById(tr.getToAccount());

		if (fromAccount.get().getAccType().equals("Saving") && toAccount.get().getAccType().equals("Saving")) {
			if (fromAccount.get().getBalance().compareTo(tr.getAmount()) == -1) {
				return "Insufficient Balance";
			} else {

				accountRepo.updateFromAccountBalance(tr.getFromAccount(), tr.getAmount());
				accountRepo.updateToAccountBalance(tr.getToAccount(), tr.getAmount());
				String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				tr.setTrDate(date);

				// System.out.println(1/0);
				trRepo.save(tr);
				return "Tranaction Succesfull";
			}
		} else {
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

	@Override
	public String checkMaturityStatus() {
		List<FDAccount> fdList = fdAccountRepo.findAll();
		Date date = new Date();
		for (int i = 0; i < fdList.size(); i++) {
			if(fdList.get(i).getMaturityDate().compareTo(date)==-1) {
				fdList.get(i).setAccountRenew(true);
				fdAccountRepo.save(fdList.get(i));
			}

		}
		return "All Maturity Date checked";
	}

}
