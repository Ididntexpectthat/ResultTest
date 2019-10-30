package com.ydl.mapper;

import com.ydl.entity.LayersList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LayersListMapper {
    List<LayersList> findAllByOrderById();


    LayersList selectByName(LayersList layersList);

    void insertSelective(LayersList layersList);

    void updateByNameSelective(LayersList oldLayersList, LayersList layersList);

    void updateUpdateTime(String updatetime, String name);

    void updateModifyTime(String modifytime, String name);

    void deleteLayersByName(LayersList layersList);
}
