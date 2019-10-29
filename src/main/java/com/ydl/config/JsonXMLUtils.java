package com.ydl.config;

import com.alibaba.fastjson.JSON;
import com.ydl.entity.Captcha;
import com.ydl.entity.Department;
import com.ydl.entity.User;

import java.util.Map;

public class JsonXMLUtils {
    public static String obj2json(Object obj) throws Exception {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2obj(String jsonStr, Class<T> clazz) throws Exception {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static <T> Map<String, Object> json2map(String jsonStr)     throws Exception {
            return JSON.parseObject(jsonStr, Map.class);
    }
  
    public static <T> T map2obj(Map<?, ?> map, Class<T> clazz) throws Exception {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    public static User map2User(Map<String, Object> user, Class<User> userClass) {
        return JSON.parseObject(JSON.toJSONString(user), userClass);
    }
    public static Captcha map2Captcha(Map<String, Object> captcha, Class<Captcha> captchaClass) {
        return JSON.parseObject(JSON.toJSONString(captcha), captchaClass);
    }
    public static Department map2Department(Map<String, Object> department, Class<Department> DepartmentClass) {
        return JSON.parseObject(JSON.toJSONString(department), DepartmentClass);
    }
}