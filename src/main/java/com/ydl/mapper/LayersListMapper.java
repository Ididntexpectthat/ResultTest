package com.ydl.mapper;

import com.ydl.entity.LayersList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LayersListMapper {
    List<LayersList> findAllByOrderById();


}
