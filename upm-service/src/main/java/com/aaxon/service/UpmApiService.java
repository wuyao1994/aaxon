package com.aaxon.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aaxon.domain.ShiroUser;

/**
 * @author elviswu
 */
@FeignClient("UPM-SERVICE")
public interface UpmApiService {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	ShiroUser login(@RequestParam("username") String username, @RequestParam("password") String password);

	@RequestMapping(value = "/set-user-info", method = RequestMethod.GET)
	void setShiroUserExtraInfo(@RequestBody ShiroUser shiroUser);
}
