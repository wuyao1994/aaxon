package com.aaxon.dao;

import com.aaxon.domain.SysMenu;
import com.aaxon.domain.SysMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMenuMapper {
	long countByExample(SysMenuExample example);

	int deleteByExample(SysMenuExample example);

	int deleteByPrimaryKey(String id);

	int insert(SysMenu record);

	int insertSelective(SysMenu record);

	List<SysMenu> selectByExample(SysMenuExample example);

	SysMenu selectByPrimaryKey(String id);

	int updateByExampleSelective(@Param("record") SysMenu record,
			@Param("example") SysMenuExample example);

	int updateByExample(@Param("record") SysMenu record,
			@Param("example") SysMenuExample example);

	int updateByPrimaryKeySelective(SysMenu record);

	int updateByPrimaryKey(SysMenu record);
}