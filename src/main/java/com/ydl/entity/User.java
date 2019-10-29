package com.ydl.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


/**
 * @author jinbin
 * @date 2018-07-08 20:43
 */
@Data
//@AllArgsConstructor 全参构造器
//@NoArgsConstructor  无参构造器
public class User {
    String id;
    String username;
    String password;
    String department;
    String gender;
    String tel;
//    @JsonIgnore
    String lastlogintime;
    String remarks;
    String picUrl;
}
