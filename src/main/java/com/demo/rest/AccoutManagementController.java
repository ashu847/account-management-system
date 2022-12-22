package com.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Account;
import com.demo.entity.Transaction;
import com.demo.entity.User;
import com.demo.service.AccountManagementServiceDAO;

@RestController
public class AccoutManagementController {
	
	@Autowired
	AccountManagementServiceDAO accService;
	
	
	@PostMapping("/add/account")
	public ResponseEntity<String> createAccount(@RequestBody User user){
		String response=accService.openAccount(user);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/transaction")
	public ResponseEntity<String> doTransaction(@RequestBody Transaction tr){
		String response= accService.tranctionOccur(tr);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/account/list")
	public ResponseEntity<List<Account>> getAllAccount(){
		List<Account> accList= accService.accountList();
		return ResponseEntity.ok(accList);
	}
	
	@PostMapping("/update/account")
	public ResponseEntity<String> updateAccount(@RequestBody User user){
		String response=accService.accountUpdate(user);
		return ResponseEntity.ok(response);
	}

}
