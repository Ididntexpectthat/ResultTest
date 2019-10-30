package com.ydl.api;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydl.annotation.PassToken;
import com.ydl.annotation.UserLoginToken;
import com.ydl.config.JsonXMLUtils;
import com.ydl.entity.Function;
import com.ydl.entity.FuzzySearch;
import com.ydl.entity.User;
import com.ydl.service.FunctionService;
import com.ydl.service.TokenService;
import com.ydl.service.UserService;
import com.ydl.utils.Mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jinbin
 * @date 2018-07-08 20:45
 */
@RestController
@RequestMapping("user")

public class UserApi {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    Mobile mobile;
    @Autowired
    FunctionService functionService;

    /**
     * 注册，从header中取ctoken和captcha，公共
     *
     * @param user
     * @param httpServletRequest
     * @return
     */
    @PassToken
    @PostMapping(value = "/register")
    public Object register2(@RequestBody User user, HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        User userForBase = userService.findByUsername(user.getUsername());
        System.out.println(userForBase);
        String ctoken = httpServletRequest.getHeader("ctoken");
        String captcha = httpServletRequest.getHeader("captcha");
        String captchaForBase = redisTemplate.opsForValue().get(ctoken);
        System.out.println(captchaForBase);
        if (StringUtils.isEmpty(captchaForBase)) {
            jsonObject.put("message", "注册失败，验证码失效");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        if (!captchaForBase.equalsIgnoreCase(captcha)) {
            if (captchaForBase == null) {
                jsonObject.put("message", "注册失败，验证码错误");
                return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
            }
        }
        if (userForBase != null) {
            jsonObject.put("message", "注册失败,用户已存在");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        if (captchaForBase.equalsIgnoreCase(captcha) && userForBase == null) {
            userService.insertUserSelective(user);
            User userInfo = userService.findByUsername(user.getUsername());
            redisTemplate.delete(user.getUsername());
            jsonObject.put("message", "注册成功");
            jsonObject.put("user", userInfo);
            return new ResponseEntity(jsonObject, HttpStatus.OK);
        }
        jsonObject.put("message", "未知错误，请联系作者！");
        return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
    }
    @UserLoginToken
    @PostMapping(value = "/addUser")
    public Object addUser(@RequestBody User user) {
        User userForBase = userService.findByUsername(user.getUsername());
        JSONObject jsonObject = new JSONObject();
        if (userForBase != null) {
            jsonObject.put("message", "注册失败,用户已存在");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        if(userForBase == null){
            userService.insertUserSelective(user);
            jsonObject.put("message","添加成功");
            return new ResponseEntity(jsonObject, HttpStatus.OK);
        }
        jsonObject.put("message", "未知错误，请联系作者！");
        return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
    }

    /**
     * 登录,从header中取ctoken和captcha，公共
     *
     * @param user
     * @param httpServletRequest
     * @return
     */
//    @UserLoginToken
//    @PostMapping("/login")
//    public Object login(@RequestBody User user, HttpServletRequest httpServletRequest){
//        JSONObject jsonObject=new JSONObject();
//        User userForBase=userService.findByUsername(user);
//        String ctoken = httpServletRequest.getHeader("ctoken");
//        String captcha = httpServletRequest.getHeader("captcha");
//        Captcha captchaForBase = captchaMapper.findByCtoken(ctoken);
////        captchaMapper.findBy
//        if(captchaForBase == null && userForBase==null){
//            jsonObject.put("message","登陆失败，验证码已经失效");
//            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//        }
//        else if(captchaForBase == null && userForBase!=null){
//            jsonObject.put("message","登陆失败，验证码已经失效");
//            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//        }
//        else if(captchaForBase != null && userForBase==null){
//            jsonObject.put("message","登录失败,用户不存在");
//            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//        }else {
//            if (!userForBase.getPassword().equals(user.getPassword()) && captchaForBase.getCaptcha().equals(captcha)){
//                jsonObject.put("message","登录失败,密码错误");
//                return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//            }else if(userForBase.getPassword().equals(user.getPassword()) && captchaForBase.getCaptcha().equals(captcha)) {
//                captchaMapper.DeleteCaptchaByCtoken(ctoken);
//                Date date = new Date();
//                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//                System.out.println(dateFormat.format(date));
//                String logintime = dateFormat.format(date);
//                userService.updateLastLoginTime(logintime,user);
//                String token = UUID.randomUUID().toString().replaceAll("-","");
//                productToken.productToken(user.getUsername(),token);
//                jsonObject.put("token", token);
//                jsonObject.put("user", userForBase);
//                return new ResponseEntity(jsonObject, HttpStatus.OK);
//            }else if(userForBase.getPassword().equals(user.getPassword()) && !captchaForBase.getCaptcha().equals(captcha)){
//                jsonObject.put("message","登录失败,验证码错误");
//                return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//            }else{
//                jsonObject.put("message","登录失败,验证码错误");
//                return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//            }
//        }
//    }
//    @PassToken
//    @PostMapping("/login")
//    public Object login(@RequestBody User user, HttpServletRequest httpServletRequest) {
//        JSONObject jsonObject = new JSONObject();
//        String ctoken = httpServletRequest.getHeader("ctoken");
//        String captcha = httpServletRequest.getHeader("captcha");
//
//        if (StringUtils.isEmpty(captcha)) {
//            jsonObject.put("message", "验证码不能为空！");
//            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//        }
//        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
//            jsonObject.put("message", "用户名或者密码不能为空！");
//            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//        }
//        Captcha captchaForBase = captchaMapper.findByCtoken(ctoken);
//        if (StringUtils.isEmpty(captchaForBase)) {
//            jsonObject.put("message", "验证码已经失效！");
//            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//        }
//        if (!captchaForBase.getCaptcha().equals(captcha)) {
//            jsonObject.put("message", "登录失败，验证码错误!");
//            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//        }
//        User userForBase = userService.login(user);
//        if (userForBase != null) {
//            captchaMapper.DeleteCaptchaByCtoken(ctoken);
//            Date date = new Date();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//            System.out.println(dateFormat.format(date));
//            String logintime = dateFormat.format(date);
//            userService.updateLastLoginTime(logintime, user);
//            String token = UUID.randomUUID().toString().replaceAll("-", "");
//            productToken.productToken(user.getUsername(), token,30);
//            jsonObject.put("token", token);
//            jsonObject.put("user", userForBase);
//            return new ResponseEntity(jsonObject, HttpStatus.OK);
//        }
//        if (userForBase == null) {
//            jsonObject.put("message", "登录失败，用户名或者密码错误!");
//            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//        }
//        jsonObject.put("message", "未知错误，请联系作者！");
//        return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
//    }
    @PassToken
    @PostMapping("/login")
    public Object test(@RequestBody User user, HttpServletRequest httpServletRequest) {
        JSONObject jsonObject = new JSONObject();
        String ctoken = httpServletRequest.getHeader("ctoken");
        String captcha = httpServletRequest.getHeader("captcha");

        if (StringUtils.isEmpty(captcha)) {
            jsonObject.put("message", "验证码不能为空！");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            jsonObject.put("message", "用户名或者密码不能为空！");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        User userForBase = userService.login(user);
        String redisCaptcha = redisTemplate.opsForValue().get(ctoken);
        if (StringUtils.isEmpty(redisCaptcha)) {
            jsonObject.put("message", "验证码已经失效！");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        if (!redisCaptcha.equalsIgnoreCase(captcha)) {
            jsonObject.put("message", "登录失败，验证码错误!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        System.out.println(redisCaptcha);
        if (redisCaptcha.equalsIgnoreCase(captcha) && userForBase != null) {
            redisTemplate.delete(user.getUsername());
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            String logintime = dateFormat.format(date);
            userService.updateLastLoginTime(logintime, user);
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            tokenService.redisSaveToken(user.getUsername(), token, 30);
            List<Function> functions = functionService.findFunctionsByUsername(user.getUsername());
            jsonObject.put("function", functions);
            jsonObject.put("token", token);
            return new ResponseEntity(jsonObject, HttpStatus.OK);
        }
        if (userForBase == null) {
            jsonObject.put("message", "登录失败，用户名或者密码错误!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        jsonObject.put("message", "未知错误，请联系作者！");
        return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
    }


    /**
     * //查询某个部门的所有成员信息,公共
     *
     * @param user
     * @param httpServletRequest
     * @param
     * @return
     */

    @UserLoginToken
    @PostMapping(value = "/queryByDepartment")
    public Object queryByDepartment(@RequestBody User user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String pagenum = httpServletRequest.getHeader("pagenum");
        if (StringUtils.isEmpty(pagenum)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "页码不能为空！!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(user.getDepartment())) {
            jsonObject.put("message", "部门不能为空！");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        if (Objects.isNull(pagenum)) {
            jsonObject.put("message", "页码不能为空！");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        Page page = PageHelper.startPage(Integer.parseInt(pagenum), 10);
        List<User> userList = userService.queryByDepartmentOrderById(user);
        PageInfo info = new PageInfo<>(page.getResult());
        String pageCount = info.getPages() + "";
        httpServletResponse.setHeader("pageCount", pageCount);
        jsonObject.put("user", userList);
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param user
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/queryByUsername")
    public Object queryByUsername(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "用户名不能为空!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(userService.findByUsername(user.getUsername()), HttpStatus.OK);
    }

    /**
     * 编辑用户信息
     *
     * @param models
     * @return
     */
    @UserLoginToken
    @PostMapping("/updateUserByName")
    public Object updateUserByName(@RequestBody Map<String, Object> models) {
        JSONObject jsonObject = new JSONObject();
        User user1 = JsonXMLUtils.map2User((Map<String, Object>) models.get("user1"), User.class);
        User user2 = JsonXMLUtils.map2User((Map<String, Object>) models.get("user2"), User.class);
        if (StringUtils.isEmpty(user1.getUsername()) || StringUtils.isEmpty(user2.getUsername())) {
            jsonObject.put("message", "用户名不能为空！");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        System.out.println("我进来了");
        System.out.println(user1.getUsername());
        System.out.println(user2);
        userService.updateByUsernameSelective(user1, user2);
        jsonObject.put("message", "修改成功！");
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }

    /**
     * 模糊查询
     *
     * @param
     */
    @UserLoginToken
    @PostMapping(value = "/fuzzyQuery")
    public Object fuzzyQuery(@RequestBody FuzzySearch fuzzySearch) {
        if (StringUtils.isEmpty(fuzzySearch.getKeyword())) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "关键词不能为空!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<List>(userService.fuzzyQueryOrderById(fuzzySearch.getKeyword()), HttpStatus.OK);
    }

    /**
     * 退出登录操作，公共
     */
    @PostMapping(value = "/exitLogin")
    public Object extiLogin(@RequestBody User user) {
        System.out.println("我进来了");
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(user.getUsername())) {
            jsonObject.put("message", "用户名不能为空");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        String token = redisTemplate.opsForValue().get(user.getUsername());
        if (!StringUtils.isEmpty(token)) {
            redisTemplate.delete(user.getUsername());
            jsonObject.put("message", "退出成功");
            return new ResponseEntity(jsonObject, HttpStatus.OK);
        }
        if (StringUtils.isEmpty(token)) {
            jsonObject.put("message", "退出成功");
            return new ResponseEntity(jsonObject, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }
    @UserLoginToken
    @PostMapping(value = "/getLayers")
    public Object getLayers(@RequestBody User user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String pagenum = httpServletRequest.getHeader("pagenum");
        if (StringUtils.isEmpty(pagenum)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "页码不能为空！!");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(user.getDepartment())) {
            jsonObject.put("message", "部门不能为空！");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        if (Objects.isNull(pagenum)) {
            jsonObject.put("message", "页码不能为空！");
            return new ResponseEntity(jsonObject, HttpStatus.UNAUTHORIZED);
        }
        Page page = PageHelper.startPage(Integer.parseInt(pagenum), 10);
        List<User> userList = userService.queryByDepartmentOrderById(user);
        PageInfo info = new PageInfo<>(page.getResult());
        String pageCount = info.getPages() + "";
        httpServletResponse.setHeader("pageCount", pageCount);
        jsonObject.put("user", userList);
        return new ResponseEntity(jsonObject, HttpStatus.OK);
    }

}
