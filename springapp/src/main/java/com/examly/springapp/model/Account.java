package com.examly.springapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Account {
    @Id
    private Long accountId;

    private String accountNumber;
    private Long balance;
    
    @OneToOne
    private User user;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account(Long accountId, String accountNumber, Long balance) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public Account() {
		super();
	}
    
    
}
