package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Account;
import com.examly.springapp.model.User;
import com.examly.springapp.service.ApiService;

@RestController
public class ApiController {

	@Autowired
	private ApiService apiService;
	
	@PostMapping("/user")
	public ResponseEntity<Boolean> save(@RequestBody User user) {

		boolean s = apiService.saveUser(user);
		if (s) {
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
		return new ResponseEntity<>(s, HttpStatus.ALREADY_REPORTED);
	}
	
	@PostMapping("/user/{userId}/account")
	public ResponseEntity<Boolean> saveAccount(@PathVariable Long userId,@RequestBody Account account) {

		boolean s = apiService.saveAccount(account, userId);
		if (s) {
			return new ResponseEntity<>(s, HttpStatus.OK);
		}
		return new ResponseEntity<>(s, HttpStatus.ALREADY_REPORTED);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAll() {

		List<User> user = apiService.getAllUser();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {

		User User = apiService.getUserById(userId);
		return new ResponseEntity<>(User, HttpStatus.OK);
	}
	
	@GetMapping("/account/{accountId}")
	public ResponseEntity <Account> getAccountById(@PathVariable Long accountId ) {

		Account account = apiService.getByAccountId(accountId);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
	
	@GetMapping("/account/balance")
	public ResponseEntity <Long> getAccountBalanceById(@RequestParam String accountNumber ) {

		Long balance = apiService.getByAccountBalanceById(accountNumber);
		if(balance!=null) {
			return new ResponseEntity<>(balance, HttpStatus.OK);
		}
		return null;
	}
}
