package com.ydl.service;


    /**
     * 生产token,如果同一用户重复登录，token的时效重置
     *
     * @author ASUS
     */

    public interface TokenService {


    /**
     * 生成有时效的token
     * 之后调其他接口需要校验adminId和token值
     *
     * @param key
     * @param value
     */
    void redisSaveToken(String key, String value, Integer time);


}
