package com.aaxis.microservice.training.demo1.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaxis.microservice.training.demo1.domain.Account;
import com.aaxis.microservice.training.demo1.domain.UserCreateForm;
import com.aaxis.microservice.training.demo1.service.UserService;

@RestController
@RequestMapping("/rest")
public class RestUserController {
	private final static Logger	LOGGER	= LoggerFactory.getLogger(RestUserController.class);
	@Autowired
	private UserService			pUserService;



	@RequestMapping("/doLogin")
	public Account login(@ModelAttribute Account pAccount) {
		LOGGER.info("login by username: {}", pAccount.getUsername());
		Account account = pUserService.findUserByUserName(pAccount);
		if (account == null || !account.getPassword().equals(pAccount.getPassword())) {
			return null;
		}
		return account;
	}



	@PostMapping("/doRegist")
	public Account doRegist(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			LOGGER.error("user validation falid");
			// TODO
		}
		Account account = new Account();
		try {
			account.setUsername(form.getUsername());
			account.setPassword(form.getPassword());
			account.setRole(form.getRole());
			pUserService.regist(account);
		} catch (DataIntegrityViolationException e) {
			bindingResult.reject("email.exists", "Email already exists");
			// TODO
		}

		return account;
	}
}
