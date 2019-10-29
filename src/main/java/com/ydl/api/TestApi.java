package com.ydl.api;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ydl.annotation.UserLoginToken;
import com.ydl.entity.User;
import com.ydl.mapper.TestMapper;
import com.ydl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController

@RequestMapping("test")
public class TestApi {


    @Autowired
    UserService userService;
    @Autowired
    TestMapper testMapper;

    /*
    1.3.1
   超级管理员用户点击用户管理查询所有用户并进行分页

    */
//    @UserLoginToken
    @UserLoginToken
    @PostMapping(value = "/test1")
    public Object queryAllUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        return "test is not ok!";
    }

    /**
     * 根据用户名删除用户
     *
     * @param
     * @return
     */
    @PostMapping(value = "/test2")
    public Object deleteUser() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("message", "delete is ok!");


        return testMapper.count();
    }
}



