package com.aaxon.controller;

import static org.apache.shiro.SecurityUtils.getSubject;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aaxon.domain.ShiroUser;
import com.aaxon.service.UpmApiService;
import com.google.common.collect.Maps;

/**
 * @author elviswu
 */
@RestController
public class SSOController {
	@Autowired
	private UpmApiService mUpmApiService;

	@GetMapping("/user")
	public Map<String, Object> user() {
		Map<String, Object> res = Maps.newHashMap();
		if (getSubject().isAuthenticated() || getSubject().isRemembered()) {
			ShiroUser user = (ShiroUser) getSubject().getPrincipal();
			mUpmApiService.setShiroUserExtraInfo(user);
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

	@PostMapping("/user/login")
	public void login(String username, String password) {
		mUpmApiService.login(username, password);
	}

}
