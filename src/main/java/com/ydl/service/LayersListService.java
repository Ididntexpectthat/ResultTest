package com.ydl.service;

import com.ydl.entity.LayersList;
import java.util.List;


public interface LayersListService {

    /**
     * 获取所有图层
     * @return
     */
    List<LayersList> getAllLayerList();
}
