package com.ydl.service.impl;

import com.ydl.entity.Department;
import com.ydl.mapper.DepartmentMapper;
import com.ydl.mapper.FunctionMapper;
import com.ydl.mapper.UserMapper;
import com.ydl.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    FunctionMapper functionMapper;
    public void insertDepartment(Department department) {
        departmentMapper.insertDepartment(department.getDepartment());
    }
    public Department findDeByname(Department department){
        return  departmentMapper.findAllBydepartment(department.getDepartment());
    }
    @Transactional
    public void deleteDepartment(Department department) {
        functionMapper.deleteFunctionByDepartment(department.getDepartment());
        userMapper.deleteUserByDepartment(department.getDepartment());
        departmentMapper.deleteDepartmentByDepartment(department.getDepartment());
    }
    //修改科室名称
    @Transactional
    public void updateDepartment(Department department1,Department department2) {
        userMapper.updateDepartmentByDepartment(department1.getDepartment(),department2.getDepartment());
        departmentMapper.deleteDepartmentByDepartment(department1.getDepartment());
//        departmentMapper.updateDepartment(department1.getDepartment(),department2.getDepartment());
    }

    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment();
    }
}
