package com.ydl.common;

public enum ServiceResultEnum {
    ERROR("error"),

    SUCCESS("success"),

    SAME_NAME_EXIST("用户名已存在！"),

    LOGIN_NAME_NULL("请输入登录名！"),

    LOGIN_PASSWORD_NULL("请输入密码！"),

    LOGIN_VERIFY_CODE_NULL("请输入验证码！"),

    LOGIN_VERIFY_CODE_ERROR("验证码错误！"),

    LOGIN_ERROR("登录失败！"),

    TOKEN_VERIFY_CODE_ERROR("token验证失败！"),

    TOKEN_NOT_NULL("请输入token！"),

    PAGENUM_NOT_NULL("请输入页码！"),

    USERNAME_NULL("请输入用户名！"),



    OPERATE_ERROR("操作失败！"),

    DB_ERROR("database error");





    private String result;

    ServiceResultEnum(String result) {
        this.result = result;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
