package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
	
	Account findByAccountNumber(String accountNumber);
}

