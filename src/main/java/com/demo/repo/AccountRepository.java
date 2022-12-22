package com.demo.repo;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entity.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query(value="select MAX(accountNo) as accountNo from Account a order by a.accountNo DESC")
	long getMaxAccountNo();
	
	@Query(value="select MAX(accountNo) as accountNo from Account a order by a.accountNo DESC")
	String checkNullAccountNo();
	
	@Modifying
	@Query(value="update Account ac set ac.balance=ac.balance + ?2  where ac.accountNo=?1")
	void updateToAccountBalance(long toaccount, BigDecimal amount) ;
	
	@Modifying
	@Query(value="update Account ac set ac.balance=ac.balance - ?2  where ac.accountNo=?1")
	void updateFromAccountBalance(long fromaccount, BigDecimal amount) ;

}
