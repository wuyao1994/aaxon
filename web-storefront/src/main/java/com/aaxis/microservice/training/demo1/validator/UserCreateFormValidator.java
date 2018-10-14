package com.aaxis.microservice.training.demo1.validator;

import com.aaxis.microservice.training.demo1.domain.UserCreateForm;
import com.aaxis.microservice.training.demo1.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserCreateFormValidator implements Validator {
	private static final Logger	LOGGER	= LoggerFactory.getLogger(UserCreateFormValidator.class);

	@Autowired
	private UserServiceImpl userService;



	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UserCreateForm.class);
	}



	@Override
	public void validate(Object o, Errors errors) {
		LOGGER.debug("Validating {}", o);
		UserCreateForm form = (UserCreateForm) o;
		validatePassword(errors, form);
		validateEmail(errors, form);
	}



	private void validatePassword(Errors errors, UserCreateForm form) {
		if (!form.getPassword().equals(form.getPasswordRepeated())) {
			errors.reject("password.mo_match", "password do not match");
		}
	}



	private void validateEmail(Errors errors, UserCreateForm form) {
		if (userService.findUserByUserName(form.getUsername()).isPresent()) {
			errors.reject("email.exists", "email already exists");
		}
	}
}
