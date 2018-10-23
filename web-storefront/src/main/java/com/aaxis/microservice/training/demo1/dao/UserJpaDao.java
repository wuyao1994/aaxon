package com.aaxis.microservice.training.demo1.dao;

import com.aaxis.microservice.training.demo1.domain.Account;
import com.aaxis.microservice.training.demo1.domain.AccountJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaDao extends JpaRepository<AccountJpa, Long> {
    public Optional<AccountJpa> findByUsername(String username);
}
