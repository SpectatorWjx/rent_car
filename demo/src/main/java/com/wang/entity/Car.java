package com.wang.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Car {
    private Integer id;

    private String carName;

    private String carType;

    private String carImage;

    private String carSeat;

    private BigDecimal carRentprice;

    private BigDecimal carDeposit;

    private Integer rentDiscount;

    private Integer rentDegree;

    private BigDecimal carPrice;

    private String carDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName == null ? null : carName.trim();
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType == null ? null : carType.trim();
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage == null ? null : carImage.trim();
    }

    public String getCarSeat() {
        return carSeat;
    }

    public void setCarSeat(String carSeat) {
        this.carSeat = carSeat == null ? null : carSeat.trim();
    }

    public BigDecimal getCarRentprice() {
        return carRentprice;
    }

    public void setCarRentprice(BigDecimal carRentprice) {
        this.carRentprice = carRentprice;
    }

    public BigDecimal getCarDeposit() {
        return carDeposit;
    }

    public void setCarDeposit(BigDecimal carDeposit) {
        this.carDeposit = carDeposit;
    }

    public Integer getRentDiscount() {
        return rentDiscount;
    }

    public void setRentDiscount(Integer rentDiscount) {
        this.rentDiscount = rentDiscount;
    }

    public Integer getRentDegree() {
        return rentDegree;
    }

    public void setRentDegree(Integer rentDegree) {
        this.rentDegree = rentDegree;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription == null ? null : carDescription.trim();
    }

}