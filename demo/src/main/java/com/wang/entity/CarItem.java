package com.wang.entity;

import java.util.Date;

public class CarItem {
    private Integer id;

    private Integer carId;

    private String carNumber;

    private String carState;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState == null ? null : carState.trim();
    }

}