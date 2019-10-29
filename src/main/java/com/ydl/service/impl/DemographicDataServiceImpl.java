package com.ydl.service.impl;

import com.ydl.entity.DemographicData;
import com.ydl.mapper.DemographicDataMapper;
import com.ydl.service.DemographicDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemographicDataServiceImpl implements DemographicDataService {
    @Autowired
    DemographicDataMapper demographicDataMapper;
    @Override
    public Integer sum(DemographicData demographicData) {
        return demographicDataMapper.sum(demographicData);
    }

    @Override
    public List<DemographicData> getAllDemograhicData() {
        return demographicDataMapper.getAllDemograhicData();
    }

    @Override
    public List<DemographicData> getAllSelective(DemographicData record) {
        return demographicDataMapper.getAllSelective(record);
    }
}
