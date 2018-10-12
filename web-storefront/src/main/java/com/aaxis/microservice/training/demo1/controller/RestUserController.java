package com.aaxis.microservice.training.demo1.controller;

import com.aaxis.microservice.training.demo1.domain.Account;
import com.aaxis.microservice.training.demo1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestUserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(RestUserController.class);
    @Autowired
    private UserService pUserService;



    @RequestMapping("/doLogin")
    public Account login(@ModelAttribute Account pAccount) {
        Account account = pUserService.findUserByUserName(pAccount);
        if (account == null || !account.getPassword().equals(pAccount.getPassword())) {
            return null;
        }
        return account;
    }



    @PostMapping("/doRegist")
    public Account doRegist(@ModelAttribute Account pAccount) {
        // validation, TODO

        try {
            pUserService.regist(pAccount);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return pAccount;
    }
}
