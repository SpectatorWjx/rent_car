package com.wang.entity;

import java.util.Date;

public class UserAttacheMent {
    private Integer id;

    private Integer userId;

    private String identity;

    private String sex;

    private String address;

    private String headImage;

    private String driverlicenseType;

    private String driverlicenseNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }

    public String getDriverlicenseType() {
        return driverlicenseType;
    }

    public void setDriverlicenseType(String driverlicenseType) {
        this.driverlicenseType = driverlicenseType == null ? null : driverlicenseType.trim();
    }

    public String getDriverlicenseNumber() {
        return driverlicenseNumber;
    }

    public void setDriverlicenseNumber(String driverlicenseNumber) {
        this.driverlicenseNumber = driverlicenseNumber == null ? null : driverlicenseNumber.trim();
    }

}