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
    public void insertSelective(LayersList layersList) {
        layersListMapper.insertSelective(layersList);
    }

    @Override
    public void updateByNameSelective(LayersList oldLayersList, LayersList newLayersList) {
        layersListMapper.updateByNameSelective(oldLayersList,newLayersList);
    }


    @Override
    public void updateUpdateTime(String updateTime, String name) {
        layersListMapper.updateUpdateTime(updateTime, name);
    }

    @Override
    public void updateModifyTime(String modifyTime, String name) {
        layersListMapper.updateModifyTime(modifyTime,name);
    }

    @Override
    public void deleteLayersByName(LayersList layersList) {
        layersListMapper.deleteLayersByName(layersList);
    }


}
