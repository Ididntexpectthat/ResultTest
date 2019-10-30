package com.ydl.api;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydl.annotation.UserLoginToken;
import com.ydl.config.JsonXMLUtils;
import com.ydl.entity.LayersList;
import com.ydl.entity.User;
import com.ydl.service.LayersListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Layers")
public class LayersListApi {
    @Autowired
    LayersListService layersListService;

    //分页获取图层
    @UserLoginToken
    @PostMapping(value = "/getAllLayersListPaging")
    public Object getAllLayersListPaging(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
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

    /**
     * 获取所有图层
     *
     * @param httpServletResponse
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/getAllLayersList")
    public Object getAllLayersList(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        List<LayersList> list = layersListService.getAllLayerList();
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }

    @UserLoginToken
    @PostMapping(value = "/addLayersList")
    public Object getAllLayerList(@RequestBody LayersList layersList) {
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        List<LayersList> list = layersListService.getAllLayerList();
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }

    @UserLoginToken
    @PostMapping(value = "/insertSelective")
    public Object insertSelective(@RequestBody LayersList layersList) {
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject = new JSONObject();
        System.out.println(layersListService.selectByName(layersList));
        if (layersListService.selectByName(layersList) == null) {
            layersListService.insertSelective(layersList);
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String updatetime = dateFormat.format(date);
            System.out.println(updatetime);
            layersListService.updateUpdateTime(updatetime, layersList.getName());

            jsonObject.put("message", "添加成功");
            return new ResponseEntity(jsonObject, HttpStatus.OK);
        }
        jsonObject.put("message", "添加失败，名字已经存在!");
        return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
    }

    @UserLoginToken
    @PostMapping(value = "/updateByNameSelective")
    public Object updateByNameSelective(@RequestBody Map<String,Object> models) {
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        LayersList oldLayersList = JsonXMLUtils.map2LayersList((Map<String, Object>) models.get("oldLayersList"), LayersList.class);
        LayersList newLayersList = JsonXMLUtils.map2LayersList((Map<String, Object>) models.get("newLayersList"), LayersList.class);
        JSONObject jsonObject = new JSONObject();
        System.out.println(oldLayersList.getName()+"   "+newLayersList.getName());
        layersListService.updateByNameSelective(oldLayersList,newLayersList);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String modifytime = dateFormat.format(date);
        System.out.println(modifytime);
        layersListService.updateUpdateTime(modifytime, newLayersList.getName());
        jsonObject.put("message", "修改成功");
        return new ResponseEntity(jsonObject, HttpStatus.OK);

    }
}
