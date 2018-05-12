package com.wang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wang.common.CallResult;
import com.wang.entity.Car;
import com.wang.entity.CarItem;
import com.wang.param.CarItemParam;
import com.wang.param.PageParam;
import com.wang.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/car")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping(value = "/addCar")
    @ResponseBody
    public CallResult<Boolean> addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @PostMapping(value = "/updateCar")
    @ResponseBody
    public CallResult<Boolean> updateCar(@RequestBody Car car){
        return carService.updateCar(car);
    }

    @PostMapping(value = "/deleteCar")
    @ResponseBody
    public CallResult<Boolean> deleteCar(@RequestBody Car car){
        int carId = car.getId();
        return carService.deleteCar(carId);
    }

    @PostMapping(value = "/getCar")
    @ResponseBody
    public CallResult<PageInfo> getCar(@RequestBody PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNo(), pageParam.getPageSize());
        List<Car> carList = carService.getCarList();
        PageInfo<Car> pageInfo = new PageInfo<>(carList);
        return new CallResult<>(pageInfo);
    }

    @PostMapping(value = "/addCarItem")
    @ResponseBody
    public CallResult<Boolean> addCarItem(@RequestBody CarItem carItem){
        return carService.addCarItem(carItem);
    }


    @PostMapping(value = "/deleteCarItem")
    @ResponseBody
    public CallResult<Boolean> deleteCarItem(@RequestBody CarItem carItem){
        int carItemId = carItem.getId();
        return carService.deleteCarItem(carItemId);
    }

    @PostMapping(value = "/getCarItem")
    @ResponseBody
    public CallResult<PageInfo> getCarItem(@RequestBody CarItemParam carItemParam) {
        PageHelper.startPage(carItemParam.getPageNo(), carItemParam.getPageSize());
        List<CarItem> carItemList = carService.getCarItemList(carItemParam.getCarId());
        PageInfo<CarItem> pageInfo = new PageInfo<>(carItemList);
        return new CallResult<>(pageInfo);
    }
}
