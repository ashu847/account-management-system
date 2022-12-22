package com.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value="select MAX(userId) as userId from User u order by u.userId DESC")
	int getMaxUserId();
	
	@Query(value="select MAX(userId) as userId from User u order by u.userId DESC")
	String checkNullUserId();
}
