package com.wang.common.config;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UserToken extends UsernamePasswordToken {

    //登录类型，判断是学生登录，教师登录还是管理员登录
    private String loginType;

    public UserToken(final String username, final String password,String loginType) {
        super(username,password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
