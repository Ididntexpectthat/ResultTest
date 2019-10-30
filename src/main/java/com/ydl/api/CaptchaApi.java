package com.ydl.api;

import com.google.code.kaptcha.impl.DefaultKaptcha;

import com.ydl.mapper.CaptchaMapper;
import com.ydl.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/Captcha")
public class CaptchaApi {
    @Autowired
    private DefaultKaptcha producer;
    @Autowired
    private CaptchaMapper captchaMapper;
    @Autowired
    TokenService tokenService;
    /**
     * 获取验证码
     *
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @PostMapping("/getCaptcha")
    public void getcaptcha(HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        String cToken = UUID.randomUUID().toString();
        try {

            // 生产验证码字符串并保存到redis中
            String createText = producer.createText();
            System.out.println(cToken+createText);
//            httpServletRequest.getSession().setAttribute("rightCode", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = producer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            tokenService.redisSaveToken(cToken,createText,2);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setHeader("ctoken", cToken);
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
        //TimerTask实现30分钟后从session中删除checkCode
//        final Timer timer=new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                captchaMapper.DeleteCaptchaByCtoken(cToken);
//                System.out.println("captcha删除成功");
//                timer.cancel();
//            }
//        },60*2*1000);
    }
//    @PostMapping("/test")
//    public Object test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
//        String ctoken = (httpServletRequest.getHeader("ctoken"));
//        return redisTemplate.opsForValue().get(ctoken);;
//    }


}
