package com.ydl.api;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydl.annotation.UserLoginToken;
import com.ydl.entity.DemographicData;
import com.ydl.mapper.DemographicDataMapper;
import com.ydl.service.DemographicDataService;
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
@RequestMapping("Demographicdata")
public class DemographicdataApi {
    @Autowired
    DemographicDataService demographicDataService;
    @Autowired
    DemographicDataMapper demographicDataMapper;

//    @UserLoginToken
    @PostMapping("/getAll")
    public Object getAllDemograhicData(@RequestBody DemographicData demographicData) {
//            return new ResponseEntity(demographicDataMapper.selectByPrimaryKey(demographicData.get编号()),HttpStatus.OK);
        return new ResponseEntity<List>(demographicDataService.getAllSelective(demographicData), HttpStatus.OK);
    }

    @UserLoginToken
    @PostMapping("/getAllPaging")
    public Object getAllDemograhicDataPaging(@RequestBody DemographicData demographicData, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//            return new ResponseEntity(demographicDataMapper.selectByPrimaryKey(demographicData.get编号()),HttpStatus.OK);
        String pagenum = httpServletRequest.getHeader("pagenum");
        if (StringUtils.isEmpty(pagenum)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "页码不能为空！!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        Page page = PageHelper.startPage(Integer.parseInt(pagenum), 10);
        List<DemographicData> list = demographicDataService.getAllDemograhicData();
//        PageInfo<DemographicData> info = new PageInfo<>(list);
        PageInfo info = new PageInfo<>(page.getResult());
//        System.out.println(info);
        String pageCount = info.getPages()  + "";
        httpServletResponse.setHeader("pageCount", pageCount);
        return new ResponseEntity(info, HttpStatus.OK);
    }
}
