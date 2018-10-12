package com.aaxis.microservice.training.demo1.controller;

import com.aaxis.microservice.training.demo1.domain.Account;
import com.aaxis.microservice.training.demo1.service.UserService;
import com.aaxis.microservice.training.demo1.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService pUserService;



    @RequestMapping("/doLogin")
    public String login(@ModelAttribute Account pAccount, HttpServletRequest request) {
        Account account = ((RestUserController) SpringUtil.getBean("restUserController")).login(pAccount);
        if (account == null) {
            request.setAttribute("errorMessage", "Login error");
            return "forward:/login";
        }
        request.getSession().setAttribute("user", account);
        return "redirect:/index";
    }



    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {

        request.getSession().removeAttribute("user");

        return "redirect:/login";
    }



    @RequestMapping("/index")
    public String index() {
        return "index";
    }



    @RequestMapping("/login")
    public String login() {
        return "login";
    }



    @RequestMapping("/regist")
    public String regist() {
        return "regist";
    }



    @PostMapping("/doRegist")
    public String doRegist(@ModelAttribute Account pAccount, HttpServletRequest request) {
        // validation, TODO

        try {
            Account u = ((RestUserController) SpringUtil.getBean("restUserController")).doRegist(pAccount);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            return "forward:/regist";
        }
        request.getSession().setAttribute("user", pAccount);
        return "redirect:/index";
    }
}
