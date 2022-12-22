package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
