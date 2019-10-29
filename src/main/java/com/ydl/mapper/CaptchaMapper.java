package com.ydl.mapper;


import com.ydl.entity.Captcha;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CaptchaMapper {

    void add(@Param("ctoken") String ctoken, @Param("captcha") String captcha);

    Captcha findByCtoken(@Param("ctoken") String ctoken);

    void DeleteCaptchaByCtoken(@Param("ctoken") String ctoken);

    Captcha Verification(@Param("captcha")String captcha,@Param("ctoken")String ctoken);
}
