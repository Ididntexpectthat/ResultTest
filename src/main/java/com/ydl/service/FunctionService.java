package com.ydl.service;

import com.ydl.entity.Function;
import java.util.List;


public interface FunctionService {


    /**
     * 根据用户名查询用户权限
     */

    List<Function> findFunctionsByUsername(String username);

    /**
     * 根据用户名编辑用户权限
     * @param function
     */
    void updateFuncByName(Function function);

    void updateFuncByNameSelective(Function function);


}
