package com.ydl.service.impl;

import com.ydl.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    public void redisSaveToken(String key, String value, Integer time) {
        Map<String, String> infoMap = new HashMap<String, String>();
        //将登陆的信息保存如redis
        redisTemplate.opsForValue().set(key, value);
        infoMap.put(key, value);
        //设置token有效的时间
        redisTemplate.expire(key, time, TimeUnit.MINUTES);
//		return infoMap;
    }
}
