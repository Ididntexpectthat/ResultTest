package com.ydl.service.impl;

import com.ydl.entity.User;
import com.ydl.mapper.FunctionMapper;
import com.ydl.mapper.UserMapper;
import com.ydl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    FunctionMapper functionMapper;

    public User login(User user){
        return userMapper.login(user.getUsername(),user.getPassword());
    }
    public User findByUsername(String username){
        return userMapper.findByUsername(username);
    }

    public User findUserById(String userId) {
        return userMapper.findUserById(userId);
    }

    public void insertUser(String username,String password,String department,String gender,String tel) {
        userMapper.insertUser(username,password,department,gender,tel);
    }

    @Override
    public void insertUserSelective(User user) {
        userMapper.insertUserSelective(user);
    }

    public void updateLastLoginTime(String lastlogintime,User user) {
        userMapper.updateLastLoginTime(lastlogintime,user.getUsername());
    }

    public List<User> findAllByOrderById(){
        return userMapper.findAllByOrderById();
    }
    public List<User> queryByDepartmentOrderById(User user){
        return userMapper.queryByDepartmentOrderById(user.getDepartment());
    }

    public List<User> fuzzyQueryOrderById(String keyword){
        return userMapper.fuzzyQueryOrderById(keyword);
    }


    public void updateUserByName(User user1,User user2) {
        userMapper.updateUserByName(user1.getUsername(),user2.getUsername(),user2.getPassword(),user2.getDepartment(),user2.getGender(),user2.getTel(),user2.getRemarks());
    }

    @Override
    public void updateByUsernameSelective(User user1, User user2) {
        userMapper.updateByUsernameSelective(user1.getUsername(),user2);
    }

    @Override
    public void updatePicUrl(String pic_url, String username) {
        userMapper.updatePicUrl(pic_url, username);
    }

    @Transactional
    public void deleteUserByUsername(User user) {
        functionMapper.deleteFunctionByusername(user.getUsername());
        userMapper.deleteUserByUsername(user.getUsername());
    }

}
