package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.FDAccount;



@Repository
public interface FDAccountRepository extends JpaRepository<FDAccount, Long> {

}
