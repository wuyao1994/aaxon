package com.aaxis.microservice.training.demo1.dao;

import com.aaxis.microservice.training.demo1.domain.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface UserDao extends ElasticsearchRepository<Account,Long> {
    public Optional<Account> findByUsername(String username);
}
