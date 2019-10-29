package com.ydl.mapper;

import com.ydl.entity.Function;
import com.ydl.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FunctionMapper {
    List<Function> findFunctionsByUsername(@Param("username")String username);

//    Function findFunctionsByUsername(String username);

    boolean existsByusername(String username);

    void updateFuncByName(@Param("username") String username,
     @Param("fun1") String fun1,@Param("fun2") String fun2,@Param("fun3") String fun3,@Param("fun4") String fun4,@Param("fun5") String fun5,
     @Param("fun6") String fun6,@Param("fun7") String fun7,@Param("fun8") String fun8,@Param("fun9") String fun9,@Param("fun10") String fun10);

    void updateFuncByNameSelective(Function record);

    void deleteFunctionByusername(@Param("username") String username);

    void deleteFunctionByDepartment(@Param("department") String department);

    void insertAllByUsername(String username,
                             String fun1, String fun2, String fun3, String fun4, String fun5,
                             String fun6,String fun7, String fun8, String fun9,String fun10);

//    void insert(Function record);

    void insertAllByUsernameSelective(Function record);
}
