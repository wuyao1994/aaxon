package com.aaxon.service.impl;

import com.aaxon.dao.SysAccountMapper;
import com.aaxon.domain.SysAccount;
import com.aaxon.domain.SysAccountExample;
import com.aaxon.service.SysAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author elviswu
 */
public class SysAccountServiceImpl implements SysAccountService {
	@Autowired
	private SysAccountMapper sysAccountMapper;



	@Override
	public List<SysAccount> selectAccountsByUsername(String username) {
		SysAccountExample exp = new SysAccountExample();
		exp.createCriteria().andLoginNameEqualTo(username);
		List<SysAccount> accounts = sysAccountMapper.selectByExample(exp);
		return accounts;
	}
}
