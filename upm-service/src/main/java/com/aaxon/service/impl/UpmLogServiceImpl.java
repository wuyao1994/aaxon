package com.aaxon.service.impl;

import com.aaxon.dao.UpmLogMapper;
import com.aaxon.domain.UpmLog;
import com.aaxon.service.UpmLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ElvisWu
 * @version: 1.0, May 15, 2019
 */
@Service
public class UpmLogServiceImpl implements UpmLogService {
	@Autowired
	private UpmLogMapper mUpmLogMapper;



	@Override
	public void insetUpmLog(UpmLog record) {
		mUpmLogMapper.insertSelective(record);
	}
}
