package com.ydl.mapper;

import com.ydl.entity.LayersList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LayersListMapper {
    List<LayersList> findAllByOrderById();


    LayersList selectByName(LayersList layersList);

    int insertSelective(LayersList layersList);

    int updateByNameSelective(LayersList oldLayersList, LayersList layersList);

    int updateUpdateTime(String updatetime, String name);

    int updateModifyTime(String modifytime, String name);

    int deleteLayersByName(LayersList layersList);
}
