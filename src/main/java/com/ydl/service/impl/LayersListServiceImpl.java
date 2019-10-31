package com.ydl.service.impl;

import com.ydl.entity.LayersList;
import com.ydl.mapper.LayersListMapper;
import com.ydl.service.LayersListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayersListServiceImpl implements LayersListService {
    @Autowired
    LayersListMapper layersListMapper;

    public List<LayersList> getAllLayerList() {
        return layersListMapper.findAllByOrderById();
    }

    @Override
    public LayersList selectByName(LayersList layersList) {
        return layersListMapper.selectByName(layersList);
    }

    @Override
    public int insertSelective(LayersList layersList) {
        return layersListMapper.insertSelective(layersList);
    }

    @Override
    public int updateByNameSelective(LayersList oldLayersList, LayersList newLayersList) {
       return layersListMapper.updateByNameSelective(oldLayersList,newLayersList);
    }


    @Override
    public int updateUpdateTime(String updateTime, String name) {
        return layersListMapper.updateUpdateTime(updateTime, name);
    }

    @Override
    public int updateModifyTime(String modifyTime, String name) {
        return layersListMapper.updateModifyTime(modifyTime,name);
    }

    @Override
    public int deleteLayersByName(LayersList layersList) {
       return layersListMapper.deleteLayersByName(layersList);
    }


}
