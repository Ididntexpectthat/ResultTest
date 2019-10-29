package com.ydl.service;

import com.ydl.entity.DemographicData;

import java.util.List;

public interface DemographicDataService {

    Integer sum(DemographicData demographicData);
    List<DemographicData> getAllDemograhicData();

    List<DemographicData> getAllSelective(DemographicData record);
}
