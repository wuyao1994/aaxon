package com.aaxis.microservice.training.demo1.service;

import java.util.Optional;

import com.aaxis.microservice.training.demo1.domain.Account;

public interface UserService {
	public void regist(Account pAccount);



	public Account findUserByUserName(Account pAccount);



	public Optional<Account> findUserByUserName(String username);
}
