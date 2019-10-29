package com.ydl.service;

import com.ydl.entity.Department;
import java.util.List;

public interface DepartmentService {
    /**
     * 插入部门
     * @param department
     */
    void insertDepartment(Department department);

    /**
     * 查询部门
     * @param department
     * @return
     */
    Department findDeByname(Department department);

    /**
     * 删除部门
     * @param department
     */
    void deleteDepartment(Department department);

    /**
     * 修改部门名称
     * @param department1
     * @param department2
     */
    void updateDepartment(Department department1,Department department2);

    /**
     * 获取所有部门名字
     * @return
     */
    List<Department> getAllDepartment();
}
