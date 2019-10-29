package com.ydl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {
    @Select("select count(*) as col_0_0_ from 人口数据.玉林市六普村级户数人口数和性别比总计表")
    String count();
}
