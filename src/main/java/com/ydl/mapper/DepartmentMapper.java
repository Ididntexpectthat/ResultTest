package com.ydl.mapper;

import com.ydl.entity.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    void insertDepartment(@Param("department") String department);

    Department findAllBydepartment(@Param("department") String department);

    void deleteDepartmentByDepartment(@Param("department") String department);

    void updateDepartment(@Param("olddepartment")String oldDepartment,@Param("department") String department1);

    List<Department> getAllDepartment();
}
