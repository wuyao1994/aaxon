package com.aaxon.controller;

import static org.apache.shiro.SecurityUtils.getSubject;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aaxon.dao.model.ShiroUser;
import com.aaxon.service.UpmApiService;
import com.google.common.collect.Maps;

/**
 * @author elviswu
 */
@RestController
@RequestMapping("/user")
public class SSOController {
	@Autowired
	private UpmApiService mUpmApiService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
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

	@PostMapping("/login")
	public void login(String username, String password) {
		mUpmApiService.login(username, password);
	}

}
