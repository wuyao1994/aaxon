package com.aaxon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aaxon.dao.model.SysPermission;
import com.aaxon.dao.model.SysPermissionExample;

/**
 * @author elvis
 */
@Mapper
public interface SysPermissionMapper {
	long countByExample(SysPermissionExample example);

	int deleteByExample(SysPermissionExample example);

	int deleteByPrimaryKey(String id);

	int insert(SysPermission record);

	int insertSelective(SysPermission record);

	List<SysPermission> selectByExample(SysPermissionExample example);

	SysPermission selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") SysPermission record,
			@Param("example") SysPermissionExample example);

	int updateByExample(@Param("record") SysPermission record,
			@Param("example") SysPermissionExample example);

	int updateByPrimaryKeySelective(SysPermission record);

	int updateByPrimaryKey(SysPermission record);
}