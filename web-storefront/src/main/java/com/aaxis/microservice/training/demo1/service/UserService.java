package com.aaxis.microservice.training.demo1.service;

import com.aaxis.microservice.training.demo1.domain.Account;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void regist(Account pAccount);

    public Account findUserByUserName(Account pAccount);

    public Optional<Account> findUserByUserName(String username);

    public void save(List<Account> pAccounts);
}
