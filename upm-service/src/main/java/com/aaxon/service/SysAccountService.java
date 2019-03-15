package com.aaxon.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aaxon.dao.model.SysAccount;

/**
 * SysAccountService interface
 * @author elviswu
 */
@FeignClient("UPM-SERVICE")
public interface SysAccountService {
	@RequestMapping("/select-account")
	List<SysAccount> selectAccountsByUsername(String username);
}
