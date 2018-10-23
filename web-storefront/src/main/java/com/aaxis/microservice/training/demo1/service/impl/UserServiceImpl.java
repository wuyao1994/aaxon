package com.aaxis.microservice.training.demo1.service.impl;

import com.aaxis.microservice.training.demo1.dao.UserDao;
import com.aaxis.microservice.training.demo1.domain.Account;
import com.aaxis.microservice.training.demo1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao mUserDao;



    @Override
    public void regist(Account pAccount) {
        Optional<Account> account = mUserDao.findByUsername(pAccount.getUsername());
        if (account.isPresent()) {
            throw new RuntimeException("Account is exists in system");
        }
        mUserDao.save(pAccount);
    }



    @Override
    public Account findUserByUserName(Account pAccount) {
        Account account = mUserDao.findByUsername(pAccount.getUsername()).get();
        return account;
    }



    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Authenticating user with username={}", username);
        Account account = mUserDao.findByUsername(username).get();
        if (account == null) {
            throw new UsernameNotFoundException(String.format("username not found '%s'.", username));
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(account.getPassword().trim());
        return new User(account.getUsername(), encodedPassword,
                AuthorityUtils.createAuthorityList(account.getRole().toString()));
    }



    @Override
    public Optional<Account> findUserByUserName(String username) {
        LOGGER.info("find user by name:", username);
        return mUserDao.findByUsername(username);
    }



    @Override
    public void save(List<Account> pAccounts) {
        mUserDao.saveAll(pAccounts);
    }
}
