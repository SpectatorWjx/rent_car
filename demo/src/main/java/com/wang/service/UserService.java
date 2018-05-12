/*
 *   阔地教育科技有限公司版权所有（codyy.com/codyy.cn ）
 *   Copyright (c) 2017, Codyy and/or its affiliates. All rights reserved.
 */
package com.wang.service;

import com.wang.common.CallResult;
import com.wang.entity.User;
import com.wang.model.AdminInfoModel;
import com.wang.model.UserInfoModel;
import com.wang.param.CodeParam;
import com.wang.param.PasswordParam;
import com.wang.param.RegisterParam;
import com.wang.param.UserAttacheMentParam;
import com.wang.result.RegisterResult;


/**
 * @author wangjiaxiang
 * @date 2018/3/20.
 */


public interface UserService {

    User getUsers(Integer userId);

    CallResult<UserInfoModel> login(String userName, String password);

    CallResult<RegisterResult> register(RegisterParam param);

    CallResult<Boolean> changePassword(PasswordParam param);

    //CallResult<Boolean> updateUserInfo(UserAttacheMentParam param);

    CallResult<Boolean> getCaptcha(CodeParam param);

    CallResult<AdminInfoModel> adminLogin(String adminName, String password);
}
