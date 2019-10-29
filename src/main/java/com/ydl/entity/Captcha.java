package com.ydl.entity;

import lombok.Data;

@Data
public class Captcha {
    private Integer id;
    private String ctoken;
    private String captcha;
}
