package com.aaxis.microservice.training.demo1.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserCreateForm {
	@NotEmpty(message = "username is empty")
	private String	username;
	@NotEmpty(message = "password is empty")
	private String	password;
	@NotEmpty(message = "password is empty")
	private String	passwordRepeated;
	@NotNull
	private Role	role;



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getPasswordRepeated() {
		return passwordRepeated;
	}



	public void setPasswordRepeated(String passwordRepeated) {
		this.passwordRepeated = passwordRepeated;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "UserCreateForm{" + "username='" + username + '\'' + ", password='" + password + '\''
				+ ", passwordRepeated='" + passwordRepeated + '\'' + ", role=" + role + '}';
	}
}
