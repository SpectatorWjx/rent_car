package com.wang.controller;

import com.wang.common.CallResult;
import com.wang.model.AdminInfoModel;
import com.wang.model.UserInfoModel;
import com.wang.param.*;
import com.wang.result.RegisterResult;
import com.wang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author wangjiaxiang
 * @date 2018/3/20.
 */
//@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param param
     * @return
     */

    @PostMapping(value = "/login")
    @ResponseBody
    public CallResult<UserInfoModel> login(@RequestBody LoginParam param) {
        String loginName = param.getUsername();
        String password = param.getPassword();
        CallResult<UserInfoModel> result = userService.login(loginName, password);
        return result;
    }


    /**
     * 退出登录
     */
    @PostMapping(value = "/logout")
    @ResponseBody
    public CallResult<Boolean> loginout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new CallResult<>(true);
    }

    /**
     * 注册
     */
    @PostMapping(value = "/register")
    @ResponseBody
    public CallResult<RegisterResult> register(@RequestBody RegisterParam param) {

        CallResult<RegisterResult> result = userService.register(param);
        return result;
    }

    /**
     * 改密
     */
    @PostMapping(value = "/changePassword")
    @ResponseBody
    public CallResult<Boolean> changePassword(@RequestBody PasswordParam param) {

        CallResult<Boolean> result = userService.changePassword(param);
        return result;
    }

//    /**
//     * 修改个人信息
//     */
//    @PostMapping(value = "/updateUserInfo")
//    @ResponseBody
//    public CallResult<Boolean> updateUser(@RequestBody UserAttacheMentParam param) {
//
//        CallResult<Boolean> result = userService.updateUserInfo(param);
//        return result;
//    }

    /**
     * 发送验证码
     */
    @PostMapping(value = "/sendCaptcha")
    @ResponseBody
    public CallResult<Boolean> updateUser(@RequestBody CodeParam param) {

        CallResult<Boolean> result = userService.getCaptcha(param);
        return result;
    }

    /**
     * 登录
     * @param param
     * @return
     */

    @PostMapping(value = "/adminLogin")
    @ResponseBody
    public CallResult<AdminInfoModel> adminLogin(@RequestBody LoginParam param) {
        String loginName = param.getUsername();
        String password = param.getPassword();
        CallResult<AdminInfoModel> result = userService.adminLogin(loginName, password);
        return result;
    }
}
