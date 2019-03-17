package com.aaxon.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aaxon.domain.SysAccount;

/**
 * SysAccountService interface
 * @author elviswu
 */
public interface SysAccountService {
	List<SysAccount> selectAccountsByUsername(String username);
}
