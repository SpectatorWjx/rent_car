package com.wang.result;


import java.math.BigDecimal;

public class CarInfoResult {

    private int id;

    private String carName;

    private String carNumber;

    private String carImage;

    private BigDecimal carDeposit;

    private BigDecimal carRentprice;

    private  String carType;

    private String carBrand;

    private String carState;

    private int rentDegree= 0;

    private String carColor;

    private BigDecimal carPrice;

    private String carDescription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public BigDecimal getCarDeposit() {
        return carDeposit;
    }

    public void setCarDeposit(BigDecimal carDeposit) {
        this.carDeposit = carDeposit;
    }

    public BigDecimal getCarRentprice() {
        return carRentprice;
    }

    public void setCarRentprice(BigDecimal carRentprice) {
        this.carRentprice = carRentprice;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public int getRentDegree() {
        return rentDegree;
    }

    public void setRentDegree(int rentDegree) {
        this.rentDegree = rentDegree;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
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
        this.carDescription = carDescription;
    }
}
