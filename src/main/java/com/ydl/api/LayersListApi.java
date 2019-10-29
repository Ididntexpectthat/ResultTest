package com.ydl.api;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydl.annotation.UserLoginToken;
import com.ydl.entity.LayersList;
import com.ydl.service.LayersListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("Layers")
public class LayersListApi {
    @Autowired
    LayersListService layersListService;

    //分页获取图层
    @UserLoginToken
    @PostMapping(value = "/getAllLayerList")
    public Object getAllLayerList(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        String pagenum = httpServletRequest.getHeader("pagenum");
        if (StringUtils.isEmpty(pagenum)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "页码不能为空！!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        Page page = PageHelper.startPage(Integer.parseInt(pagenum), 10);
        List<LayersList> list = layersListService.getAllLayerList();
        PageInfo info = new PageInfo<>(page.getResult());
        String pageCount = info.getPages() + "";
        httpServletResponse.setHeader("pageCount", pageCount);
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }
    @UserLoginToken
    @PostMapping(value = "/getAll")
    public Object getAll() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "我进来了");
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }
}
