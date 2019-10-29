package com.ydl.mapper;

import com.ydl.entity.DemographicData;

import java.util.List;

public interface DemographicDataMapper {
    int deleteByPrimaryKey(Integer 编号);

    int insert(com.ydl.entity.DemographicData record);

    int insertSelective(com.ydl.entity.DemographicData record);

    com.ydl.entity.DemographicData selectByPrimaryKey(Integer 编号);

    int updateByPrimaryKeySelective(com.ydl.entity.DemographicData record);

    int updateByPrimaryKey(com.ydl.entity.DemographicData record);

    Integer sum(DemographicData demographicData);

    List<DemographicData> getAllDemograhicData();

    List<DemographicData> getAllSelective(DemographicData record);
}