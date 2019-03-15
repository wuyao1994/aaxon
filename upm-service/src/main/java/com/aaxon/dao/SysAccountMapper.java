package com.aaxon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aaxon.dao.model.SysAccount;
import com.aaxon.dao.model.SysAccountExample;

/**
 * @@author elvis
 */
@Mapper
public interface SysAccountMapper {
	long countByExample(SysAccountExample example);

	int deleteByExample(SysAccountExample example);

	int deleteByPrimaryKey(String id);

	int insert(SysAccount record);

	int insertSelective(SysAccount record);

	List<SysAccount> selectByExample(SysAccountExample example);

	SysAccount selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") SysAccount record,
			@Param("example") SysAccountExample example);

	int updateByExample(@Param("record") SysAccount record,
			@Param("example") SysAccountExample example);

	int updateByPrimaryKeySelective(SysAccount record);

	int updateByPrimaryKey(SysAccount record);
}