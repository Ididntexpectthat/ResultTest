package com.ydl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ydl.mapper")
public class KaptchaJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaptchaJwtApplication.class, args);
    }

}
