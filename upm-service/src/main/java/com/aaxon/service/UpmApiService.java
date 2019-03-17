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
public interface UpmApiService {

	ShiroUser login(String username, String password);

	void setShiroUserExtraInfo(ShiroUser shiroUser);
}
