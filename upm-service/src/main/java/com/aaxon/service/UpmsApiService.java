package com.aaxon.service;

import com.aaxon.domain.ShiroUser;

/**
 * @author elviswu
 */
public interface UpmsApiService {

	public ShiroUser login(String username, String password);

	public void setShiroUserExtraInfo(ShiroUser shiroUser);
}
