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

    int insertSelective(LayersList layersList);

    int updateByNameSelective(LayersList oldLayersList,LayersList newLayersList);

    int updateUpdateTime(String updateTime,String name);

    int updateModifyTime(String modifyTime,String name);

    int deleteLayersByName(LayersList layersList);
}
