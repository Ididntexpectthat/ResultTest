package com.ydl.service;

import com.ydl.entity.LayersList;
import java.util.List;


public interface LayersListService {

    /**
     * 获取所有图层
     * @return
     */
    List<LayersList> getAllLayerList();

    LayersList selectByName(LayersList layersList);

    void insertSelective(LayersList layersList);

    void updateByNameSelective(LayersList oldLayersList,LayersList newLayersList);

    void updateUpdateTime(String updateTime,String name);

    void updateModifyTime(String modifyTime,String name);
}
