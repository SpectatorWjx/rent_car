package com.wang.service;

import com.wang.common.CallResult;
import com.wang.entity.Car;
import com.wang.entity.CarItem;

import java.util.List;

public interface CarService {
    CallResult<Boolean> addCar(Car car);

    CallResult<Boolean> updateCar(Car car);

    CallResult<Boolean> deleteCar(int id);

    List<Car> getCarList();


    CallResult<Boolean>  addCarItem(CarItem carItem);

    CallResult<Boolean> deleteCarItem(int id);

    List<CarItem> getCarItemList(int carId);
}
