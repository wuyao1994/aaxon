package com.aaxon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.aaxon.domain.UpmLog;
import com.aaxon.domain.UpmLogExample;

@Mapper
public interface UpmLogMapper {
    long countByExample(UpmLogExample example);

    int deleteByExample(UpmLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(UpmLog record);

    int insertSelective(UpmLog record);

    List<UpmLog> selectByExampleWithBLOBs(UpmLogExample example);

    List<UpmLog> selectByExample(UpmLogExample example);

	UpmLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") UpmLog record, @Param("example") UpmLogExample example);

    int updateByExampleWithBLOBs(@Param("record") UpmLog record, @Param("example") UpmLogExample example);

    int updateByExample(@Param("record") UpmLog record, @Param("example") UpmLogExample example);

    int updateByPrimaryKeySelective(UpmLog record);

    int updateByPrimaryKeyWithBLOBs(UpmLog record);

    int updateByPrimaryKey(UpmLog record);
}