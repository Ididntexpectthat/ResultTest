package com.ydl.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ydl.annotation.PassToken;
import com.ydl.annotation.UserLoginToken;
import com.ydl.entity.User;
import com.ydl.service.TokenService;
import com.ydl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;


/**
 * @author jinbin
 * @date 2018-07-08 20:41
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    TokenService tokenService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        String username = httpServletRequest.getHeader("username");
        // 如果不是映射到方法直接通过

        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                if (StringUtils.isEmpty(token)) {
                    throw new RuntimeException("token不能为空，请输入token进行验证！");
                }
                if (StringUtils.isEmpty(token)) {
                    throw new RuntimeException("header中的username为空！");
                }
                // 执行认证
                System.out.println("token" + token);
                try {
//                    long time = redisTemplate.getExpire(username);
                    String redistoken = redisTemplate.opsForValue().get(username);
                    System.out.println("retoken:" + redistoken);
                    if(StringUtils.isEmpty(redistoken) || !redistoken.equals(token)){
                        throw new RuntimeException("登录失效，请重新登录！");
                    }
                    if (!StringUtils.isEmpty(redistoken) && redistoken.equals(token)) {
//                        String Newtoken = UUID.randomUUID().toString().replaceAll("-", "");
                        tokenService.redisSaveToken(username, redistoken,30);
                        System.out.println("校验成功");
                        return true;
                    } else {
                        throw new RuntimeException("校验失败，登录失效，请重新登录！");
//                        return false;
                    }
                } catch (Exception e) {
//                    return false;
                    throw new RuntimeException("未知错误，请与作者联系！");
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
