package com.wang.service.impl;

import com.wang.common.BaseCallResultCode;
import com.wang.common.CallResult;
import com.wang.common.MyEnum.CarState;
import com.wang.common.exception.BusinessException;
import com.wang.dao.CarItemMapper;
import com.wang.dao.CarMapper;
import com.wang.entity.Car;
import com.wang.entity.CarItem;
import com.wang.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carDao;

    @Autowired
    private CarItemMapper carItemDao;

    @Override
    public CallResult<Boolean> addCar(Car car) {
        Car newCar = carDao.selectByName(car.getCarName());

        if(newCar != null){
            throw new BusinessException(BaseCallResultCode.DB_DUPLICATEKEY_ERROR,"车辆类型已存在");
        }
        car.setRentDegree(0);
        int i = carDao.insert(car);
        if(i != 1){
            throw new BusinessException(BaseCallResultCode.DB_INSERT_ERROR);
        }
        return new CallResult<>(true);
    }

    @Override
    public CallResult<Boolean> updateCar(Car car) {
        Car newCar = carDao.selectByPrimaryKey(car.getId());

        if(newCar == null){
            throw new BusinessException(BaseCallResultCode.DB_SELECT_ERROR,"车辆类型不存在");
        }

        int i = carDao.updateByPrimaryKey(car);
        if(i != 1){
            throw new BusinessException(BaseCallResultCode.DB_UPDATE_ERROR);
        }
        return new CallResult<>(true);
    }

    @Override
    public CallResult<Boolean> deleteCar(int id) {
        Car newCar = carDao.selectByPrimaryKey(id);

        if(newCar == null){
            throw new BusinessException(BaseCallResultCode.DB_SELECT_ERROR,"车辆类型不存在");
        }

        List<CarItem>  list = carItemDao.selectCarItemByCarId(id);
        if(!list.isEmpty()){
            throw new BusinessException(BaseCallResultCode.DB_DELETE_ERROR,"该类型还存在车辆");
        }
        int i = carDao.deleteByPrimaryKey(id);
        if(i != 1){
            throw new BusinessException(BaseCallResultCode.DB_DELETE_ERROR);
        }
        return new CallResult<>(true);
    }

    @Override
    public List<Car> getCarList() {
        List<Car>  list = carDao.selectAllCar();
        return  list;
    }

    @Override
    public CallResult<Boolean> addCarItem(CarItem carItem) {
        if(carItem.getCarId() == null){
            throw new BusinessException(BaseCallResultCode.SYSTEM_ERROR);
        }
        Car car = carDao.selectByPrimaryKey(carItem.getCarId());
        if(car == null){
            throw new BusinessException(BaseCallResultCode.DB_DATA_ERROR);
        }

        CarItem newCarItem = carItemDao.selectByNumber(carItem.getCarNumber());

        if(newCarItem != null){
            throw new BusinessException(BaseCallResultCode.DB_DUPLICATEKEY_ERROR,"车辆已存在");
        }
        carItem.setCarState(CarState.NORENT.toString());
        int i = carItemDao.insert(carItem);
        if(i != 1){
            throw new BusinessException(BaseCallResultCode.DB_INSERT_ERROR);
        }
        return new CallResult<>(true);
    }


    @Override
    public CallResult<Boolean> deleteCarItem(int id) {
        CarItem newCarItem = carItemDao.selectByPrimaryKey(id);

        if(newCarItem == null){
            throw new BusinessException(BaseCallResultCode.DB_SELECT_ERROR,"车辆不存在");
        }

        if(newCarItem.getCarState().equals(CarState.ISRENT.toString())){
            throw new BusinessException(BaseCallResultCode.DB_DELETE_ERROR,"车辆已被出租");
        }
        int i = carItemDao.deleteByPrimaryKey(id);
        if(i != 1){
            throw new BusinessException(BaseCallResultCode.DB_DELETE_ERROR);
        }
        return new CallResult<>(true);
    }

    @Override
    public List<CarItem> getCarItemList(int carId) {
        List<CarItem>  list = carItemDao.selectCarItemByCarId(carId);
        return  list;
    }
}
