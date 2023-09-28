package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Account;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.AccountRepo;
import com.examly.springapp.repository.UserRepo;


@Service
public class ApiService {
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public boolean saveAccount(Account p, Long userId) {
		if(userRepo.existsById(userId))
		{
			p.setUser(userRepo.findById(userId).get());
			return accountRepo.save(p) != null ? true : false;
		}
		return false;
	}
	
	public boolean saveUser(User t) {
		return userRepo.save(t) != null ? true : false;
	}
	
	public List<User> getAllUser() {

		List<User> list = userRepo.findAll();
		return list;
	}

	public User getUserById(Long userId) {

		if (userRepo.existsById(userId)) {
			User user = userRepo.findById(userId).get();
			return user;
		}
		return null;
	}
	
	public Account getByAccountId(Long accountId) { 

		Account list = accountRepo.findById(accountId).get();;
		return list;
	}
	
	public Long getByAccountBalanceById(String accountNumber) {  
		Account account = accountRepo.findByAccountNumber(accountNumber);
		return account.getBalance();
		
	}
	
}
