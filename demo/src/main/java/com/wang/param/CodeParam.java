package com.wang.param;

import com.wang.common.MyEnum.Captcha;

public class CodeParam {
    private String phone;
    private Captcha type;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Captcha getType() {
        return type;
    }

    public void setType(Captcha type) {
        this.type = type;
    }
}
