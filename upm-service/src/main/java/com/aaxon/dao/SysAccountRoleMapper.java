package com.aaxon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aaxon.model.SysAccountRoleExample;
import com.aaxon.model.SysAccountRoleKey;

/**
 * @author elvis
 */
@Mapper
public interface SysAccountRoleMapper {

	long countByExample(SysAccountRoleExample example);

	int deleteByExample(SysAccountRoleExample example);

	int deleteByPrimaryKey(SysAccountRoleKey key);

	int insert(SysAccountRoleKey record);

	int insertSelective(SysAccountRoleKey record);

	List<SysAccountRoleKey> selectByExample(SysAccountRoleExample example);

	int updateByExampleSelective(@Param("record") SysAccountRoleKey record,
			@Param("example") SysAccountRoleExample example);

	int updateByExample(@Param("record") SysAccountRoleKey record,
			@Param("example") SysAccountRoleExample example);
}