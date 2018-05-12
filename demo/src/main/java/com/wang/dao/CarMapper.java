package com.wang.dao;

import com.wang.entity.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);

    Car selectByName(String name);

    List<Car> selectAllCar();
}