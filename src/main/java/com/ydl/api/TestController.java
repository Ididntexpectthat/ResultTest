package com.ydl.api;

import com.ydl.config.JsonXMLUtils;
import com.ydl.entity.Captcha;
import com.ydl.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TestController {
    /*
    json中传多个对象
     */
    @RequestMapping("/test")
    @ResponseBody
    public Object test(@RequestBody Map<String, Object> models) {
        User user = JsonXMLUtils.map2User((Map<String, Object>) models.get("user"), User.class);
        Captcha captcha = JsonXMLUtils.map2Captcha((Map<String, Object>) models.get("captcha"), Captcha.class);
        System.out.println(user.getPassword());
        System.out.println(captcha);
        return models;
    }
}