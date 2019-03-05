package com.aaxon.service;

import java.util.List;

import com.aaxon.domain.SysAccount;

/**
 * SysAccountService interface
 * @author elviswu
 */
public interface SysAccountService {
	/**
	 * select accounts
	 * @param username
	 * @return
	 */
	public List<SysAccount> selectAccountsByUsername(String username);
}
