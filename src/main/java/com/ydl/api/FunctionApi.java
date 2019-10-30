package com.ydl.api;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydl.annotation.UserLoginToken;
import com.ydl.entity.Function;
import com.ydl.service.FunctionService;
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
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("function")
public class FunctionApi {

    @Autowired
    FunctionService functionService;


    //查询某个用户的权限
    @UserLoginToken
    @PostMapping(value = "/queryFuncByName")
    public Object queryFuncByName(@RequestBody Function function, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String pagenum = httpServletRequest.getHeader("pagenum");
        if (StringUtils.isEmpty(pagenum)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "页码不能为空！!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        Page page = PageHelper.startPage(Integer.parseInt(pagenum), 10);
        List<Function> list = functionService.findFunctionsByUsername(function.getUsername());
        PageInfo info = new PageInfo<>(page.getResult());
        String pageCount = info.getPages() + "";
        httpServletResponse.setHeader("pageCount", pageCount);
        return new ResponseEntity<List>(list, HttpStatus.OK);

    }

    //根据用户名编辑用户权限
    @UserLoginToken
    @PostMapping(value = "/updateFuncByName")
    public ResponseEntity<Map<String, Object>> updateFuncByName(@RequestBody Function function) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(function.getUsername())) {
            jsonObject.put("message", "编辑失败，用户名不能为空!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        functionService.updateFuncByNameSelective(function);
        jsonObject.put("message", "编辑用户成功");
        return new ResponseEntity(HttpStatus.OK);
    }

    //分页获取图层
    @UserLoginToken
    @PostMapping(value = "/get")
    public Object get(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "进来了");
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }
}
