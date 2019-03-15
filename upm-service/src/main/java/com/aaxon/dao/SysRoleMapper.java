package com.aaxon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aaxon.model.SysRole;
import com.aaxon.model.SysRoleExample;

/**
 * @author elviswu
 */
@Mapper
public interface SysRoleMapper {
	long countByExample(SysRoleExample example);

	int deleteByExample(SysRoleExample example);

	int deleteByPrimaryKey(String id);

	int insert(SysRole record);

	int insertSelective(SysRole record);

	List<SysRole> selectByExample(SysRoleExample example);

	SysRole selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") SysRole record,
			@Param("example") SysRoleExample example);

	int updateByExample(@Param("record") SysRole record,
			@Param("example") SysRoleExample example);

	int updateByPrimaryKeySelective(SysRole record);

	int updateByPrimaryKey(SysRole record);
}