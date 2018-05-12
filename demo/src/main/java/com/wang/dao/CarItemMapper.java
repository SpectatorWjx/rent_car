package com.wang.dao;

import com.wang.entity.Car;
import com.wang.entity.CarItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarItem record);

    int insertSelective(CarItem record);

    CarItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarItem record);

    int updateByPrimaryKey(CarItem record);

    CarItem selectByNumber(String number);

    List<CarItem> selectCarItemByCarId(Integer carId);
}