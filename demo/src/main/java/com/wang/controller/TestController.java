/*
 *   阔地教育科技有限公司版权所有（codyy.com/codyy.cn ）
 *   Copyright (c) 2017, Codyy and/or its affiliates. All rights reserved.
 */
package com.wang.controller;

import com.wang.common.CallResult;
import com.wang.entity.User;
import com.wang.model.UserInfoModel;
import com.wang.param.RegisterParam;
import com.wang.param.UserParam;
import com.wang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author wangjiaxiang
 * @date 2018/3/20.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private UserService userService;
//
//    @Autowired
//    private CarItemMapper carItemMapper;

    //返回jsp视图展示
    @RequestMapping(value = "/getUserModel",method = RequestMethod.GET)
    public ModelAndView getUsers1(@RequestParam String phone , @RequestParam String password) {

        CallResult<UserInfoModel> result = userService.login(phone, password);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("getUsers");
        modelAndView.addObject("result",result);
        return modelAndView;
    }

    //返回json格式数据，形式
    @RequestMapping(value = "/getUserJson",method = RequestMethod.POST)
    @ResponseBody
    public CallResult<User> getUsers2(@RequestBody UserParam param) {
        int id = param.getId();
        User users = userService.getUsers(id);
        User user =users;
        return new CallResult<>(user);
    }

    //返回json格式数据，形式
    @RequestMapping(value = "/getSession",method = RequestMethod.POST)
    @ResponseBody
    public CallResult<Boolean> getSession(@RequestBody RegisterParam param) {
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(param.getPhone(),"1234");
        session.setTimeout(10*60*1000);
        return new CallResult<>(true);
    }

//    //返回json格式数据，形式
//    @RequestMapping(value = "/getCar",method = RequestMethod.POST)
//    @ResponseBody
//    public CallResult<PageInfo<CarItem>> checkSession(@RequestBody PageParam pageParam) {
//        PageHelper.startPage(pageParam.getPageNo(),pageParam.getPageSize());
//        List<CarItem> carList = carItemMapper.selectAllCar();
//        PageInfo<CarItem> pageInfo = new PageInfo<>(carList);
//        return new CallResult<>(pageInfo);
//    }

}
