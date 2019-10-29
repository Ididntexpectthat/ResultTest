package com.ydl.utils;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("Mobile")
public class Mobile {
    /**
     * 手机号验证
     * @param str
     * @return 验证通过返回true
     */
    public boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, EncoderException {
        MD5 md5 = new MD5();

        String s1 = DigestUtils.md5Hex("123456!&你好");
        String s2 = DigestUtils.md5Hex(s1);
        System.out.println(s1);
        System.out.println(s2);
        final Base64.Encoder encoder = Base64.getEncoder();
        System.out.println(encoder.encodeToString(s1.getBytes()));
    }
}
