package com.ydl.mapper;


import com.ydl.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author jinbin
 * @date 2018-07-08 20:44
 */
@Mapper
public interface UserMapper {

    User findByUsername(@Param("username") String username);

    User findUserById(@Param("id") String id);

    void insertUser(@Param("username") String username,@Param("password") String password,@Param("department") String department,@Param("gender") String gender,@Param("tel") String tel);

    void insertUserSelective(User record);

    List<User> findAllByOrderById();

    List<User> queryByDepartmentOrderById(@Param("department")String department);



    void updateLastLoginTime(@Param("lastlogintime") String lastlogintime,@Param("username") String username);

//    void updateUserByName(@Param("username") String username,@Param("password") String password,@Param("department") String department,@Param("gender") String gender,@Param("tel") String tel,@Param("remarks") String remarks);

    void updatePicUrl(String pic_url,String username);

    void deleteUserByUsername(@Param("username") String username);

    List<User> fuzzyQueryOrderById(@Param("keyword") String keyword);

    void deleteUserByDepartment(@Param("department") String department);

    void updateUserByName(String oldusername, String username, String password, String department, String gender, String tel, String remarks);

    void updateByUsernameSelective(String oldusername,User record);

    void updateDepartmentByDepartment(@Param("olddepartment") String oldDepartment,@Param("department") String department);

    User login(@Param("username") String username,@Param("password") String password);

}
