package com.aaxon.controller;

import static org.apache.shiro.SecurityUtils.getSubject;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaxon.domain.ShiroUser;
import com.aaxon.service.UpmsApiService;
import com.google.common.collect.Maps;

/**
 * @author elviswu
 */
@RestController
@RequestMapping("/user")
public class SSOController {
	@Autowired
	private UpmsApiService mUpmsApiService;

	@RequestMapping
	public Map<String, Object> user() {
		Map<String, Object> res = Maps.newHashMap();
		if (getSubject().isAuthenticated() || getSubject().isRemembered()) {
			ShiroUser user = (ShiroUser) getSubject().getPrincipal();
			mUpmsApiService.setShiroUserExtraInfo(user);
			res.put("user", user);
			res.put("menu", user.getMenus());
			return res;
		}
		else {
			res.put("user", null);
			res.put("menu", null);
			return res;
		}

	}

	@PostMapping("/login")
	public void login(String username, String password) {
		mUpmsApiService.login(username, password);
	}

}