package com.aaxon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aaxon.dao.SysAccountMapper;
import com.aaxon.dao.model.SysAccount;
import com.aaxon.dao.model.SysAccountExample;
import com.aaxon.service.SysAccountService;

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
