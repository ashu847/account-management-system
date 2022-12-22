package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.SavingsAccount;



public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

}
