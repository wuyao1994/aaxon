package com.aaxon.dao;

import com.aaxon.domain.SysAccount;
import com.aaxon.domain.SysAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author elvis
 */
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