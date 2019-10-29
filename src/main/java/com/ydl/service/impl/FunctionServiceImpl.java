package com.ydl.service.impl;

import com.ydl.entity.Function;
import com.ydl.mapper.FunctionMapper;
import com.ydl.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionServiceImpl implements FunctionService {
    @Autowired
    FunctionMapper functionMapper;

    //根据用户名查询用户权限
    public List<Function> findFunctionsByUsername(String username) {
        return functionMapper.findFunctionsByUsername(username);
    }

    //根据用户名编辑用户权限
    public void updateFuncByName(Function function) {
        if(!functionMapper.existsByusername(function.getUsername())){
            functionMapper.insertAllByUsername(function.getUsername(),function.getFun1(),function.getFun2(),function.getFun3(),function.getFun4(),function.getFun5(),function.getFun6(),function.getFun7(),function.getFun8(),function.getFun9(),function.getFun10());
        }
        else {
            functionMapper.updateFuncByName(function.getUsername(), function.getFun1(), function.getFun2(), function.getFun3(), function.getFun4(), function.getFun5(), function.getFun6(), function.getFun7(), function.getFun8(), function.getFun9(), function.getFun10());
        }
    }

    @Override
    public void updateFuncByNameSelective(Function function) {
        if(!functionMapper.existsByusername(function.getUsername())){
            functionMapper.insertAllByUsernameSelective(function);
        }else {
            functionMapper.updateFuncByNameSelective(function);
        }
    }
}
