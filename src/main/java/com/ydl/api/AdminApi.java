package com.ydl.api;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydl.annotation.UserLoginToken;
import com.ydl.entity.User;
import com.ydl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@UserLoginToken
@RequestMapping("Admin")
public class AdminApi {

    @Autowired
    UserService userService;

    /**
     * 超级管理员用户点击用户管理查询所有用户并进行分页
     *
     * @param httpServletRequest
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/queryAllUser")
    public Object queryAllUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String pagenum = httpServletRequest.getHeader("pagenum");
        if (StringUtils.isEmpty(pagenum)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "页码不能为空！");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        Page page = PageHelper.startPage(Integer.parseInt(pagenum), 10);
        List<User> list = userService.findAllByOrderById();
        PageInfo info = new PageInfo<>(page.getResult());
        String pageCount = info.getPages()  + "";
        httpServletResponse.setHeader("pageCount", pageCount);
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }

    /**
     * 根据用户名删除用户
     *
     * @param user
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/deleteUser")
    public Object deleteUser(@RequestBody User user) {
        JSONObject jsonObject = new JSONObject();
        if (userService.findByUsername(user.getUsername()) == null) {
            jsonObject.put("message", "用户不存在!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        } else {
            try {
                System.out.println(user.getUsername());
                userService.deleteUserByUsername(user);
                jsonObject.put("message", "删除成功！");
                return new ResponseEntity(jsonObject, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity(e, HttpStatus.UNAUTHORIZED);
            }
        }

    }
}

