package com.ydl.service;


import com.ydl.entity.User;
import java.util.List;

/**
 * @author jinbin
 * @date 2018-07-08 20:52
 */
public interface UserService {

    /**
     * 登录验证
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);

    User findUserById(String userId);

    /**
     * 注册
     * @param username
     * @param password
     * @param department
     * @param gender
     * @param tel
     */
    void insertUser(String username, String password, String department, String gender, String tel);

    void insertUserSelective(User user);

    /**
     * 更新最后登录时间
     * @param lastlogintime
     * @param user
     */
    void updateLastLoginTime(String lastlogintime, User user);

    /**
     * 查询所有用户的信息
     * @return
     */
    List<User> findAllByOrderById();

    /**
     * 查询某部门的用户信息
     * @param user
     * @return
     */
    List<User> queryByDepartmentOrderById(User user);

    /**
     * 模糊查询
     * @param keyword
     * @return
     */
    List<User> fuzzyQueryOrderById(String keyword);

    /**
     * 修改用户的信息
     * @param user1
     * @param user2
     */
    void updateUserByName(User user1, User user2);
    void updateByUsernameSelective(User user1,User user2);

    void updatePicUrl(String pic_url,String username);
    /**
     * 删除用户
     * @param user
     */
    void deleteUserByUsername(User user);



}
