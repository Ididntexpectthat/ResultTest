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
    public List<LayersList> getAllLayerList()
    {
        return layersListMapper.findAllByOrderById();
    }


}
