package com.wang.dao;

import com.wang.entity.UserAttacheMent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAttacheMentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAttacheMent record);

    int insertSelective(UserAttacheMent record);

    UserAttacheMent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAttacheMent record);

    int updateByPrimaryKey(UserAttacheMent record);
}