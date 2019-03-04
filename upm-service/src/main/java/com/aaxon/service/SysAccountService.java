package com.aaxon.service;


import com.aaxon.domain.SysAccount;

import java.util.List;

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
