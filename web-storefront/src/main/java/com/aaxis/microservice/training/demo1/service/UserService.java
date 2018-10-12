package com.aaxis.microservice.training.demo1.service;

import com.aaxis.microservice.training.demo1.dao.UserDao;
import com.aaxis.microservice.training.demo1.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao mUserDao;



    public void regist(Account pAccount) {
        Account account = mUserDao.findByUsername(pAccount.getUsername());
        if (account != null) {
            throw new RuntimeException("Account is exists in system");
        }
        mUserDao.save(pAccount);
    }



    public Account findUserByUserName(Account pAccount) {
        Account account = mUserDao.findByUsername(pAccount.getUsername());
        return account;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Authenticating user with username={}", username);
        Account account = mUserDao.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(String.format("username not found '%s'.", username));
        }
        return new User(account.getUsername(), account.getPassword(),
                AuthorityUtils.createAuthorityList(account.getRole().toString()));
    }
}
