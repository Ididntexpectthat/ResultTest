package com.ydl.api;

import com.alibaba.fastjson.JSONObject;
import com.ydl.annotation.UserLoginToken;
import com.ydl.config.JsonXMLUtils;
import com.ydl.entity.Department;
import com.ydl.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("department")
public class DepartmentApi {
    @Autowired
    DepartmentService departmentService;

    /**
     * 获取所有部门
     */
    @PostMapping(value = "/getAllDepartment")
    public Object getAllDepartment() {
        return new ResponseEntity<List>(departmentService.getAllDepartment(), HttpStatus.OK);
    }

    /**
     * 添加新科室
     *
     * @param department
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/insert")
    public Object insert(@RequestBody Department department) {
        JSONObject jsonObject = new JSONObject();
        if (departmentService.findDeByname(department) == null) {
            departmentService.insertDepartment(department);
            jsonObject.put("message", "科室添加成功!");
            return new ResponseEntity(jsonObject, HttpStatus.OK);
        } else {
            jsonObject.put("message", "添加失败，部门已经存在！");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 根据科室删除科室，用户，权限
     *
     * @param department
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/deleteDepartment")
    public Object deleteDepartment(@RequestBody Department department) {
        JSONObject jsonObject = new JSONObject();
        try {
            departmentService.deleteDepartment(department);
            jsonObject.put("message", "科室删除成功!");
            return new ResponseEntity(jsonObject, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e, HttpStatus.OK);
        }
    }

    /**
     * 修改科室的名称以及把科室下的用户的科室也修改
     *
     * @param models
     * @return
     */
    @UserLoginToken
    @Transactional
    @PostMapping(value = "/updateDepartment")
    public Object test(@RequestBody Map<String, Object> models) {
        JSONObject jsonObject = new JSONObject();
        Department department1 = JsonXMLUtils.map2Department((Map<String, Object>) models.get("department1"), Department.class);
        Department department2 = JsonXMLUtils.map2Department((Map<String, Object>) models.get("department2"), Department.class);
        /**
         * 先新增新的权限
         */
        if (StringUtils.isEmpty(department1.getDepartment()) || StringUtils.isEmpty(department2.getDepartment())) {
            jsonObject.put("message", "编辑失败，部门不能为空!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        if (departmentService.findDeByname(department2) == null) {
            departmentService.insertDepartment(department2);
            /**
             * 再进行修改和删除
             */
            try {
                departmentService.updateDepartment(department1, department2);
                jsonObject.put("message", "编辑部门成功!");
                return new ResponseEntity(jsonObject, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity(e, HttpStatus.UNAUTHORIZED);
            }
        } else {
            jsonObject.put("message", "编辑失败，部门已经存在");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
    }
}
