package com.aaxis.microservice.training.demo1.dao;

import com.aaxis.microservice.training.demo1.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Account,Long> {
    public Account findByUsername(String username);
}
