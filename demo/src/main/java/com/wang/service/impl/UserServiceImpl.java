/*
 *   阔地教育科技有限公司版权所有（codyy.com/codyy.cn ）
 *   Copyright (c) 2017, Codyy and/or its affiliates. All rights reserved.
 */
package com.wang.service.impl;

import com.wang.common.BaseCallResultCode;
import com.wang.common.CallResult;
import com.wang.common.MyEnum.Captcha;
import com.wang.common.MyEnum.LoginType;
import com.wang.common.config.CustomizedToken;
import com.wang.common.exception.BusinessException;
import com.wang.dao.AdminMapper;
import com.wang.dao.UserAttacheMentMapper;
import com.wang.dao.UserMapper;
import com.wang.entity.Admin;
import com.wang.entity.User;
import com.wang.entity.UserAttacheMent;
import com.wang.model.AdminInfoModel;
import com.wang.model.UserInfoModel;
import com.wang.param.CodeParam;
import com.wang.param.PasswordParam;
import com.wang.param.RegisterParam;
import com.wang.param.UserAttacheMentParam;
import com.wang.result.RegisterResult;
import com.wang.service.UserService;
import com.wang.util.EhCacheUtil;
import com.wang.util.SendMessagesUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.wang.util.Md5Password.getMD5;

/**
 * @author wangjiaxiang
 * @date 2018/3/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userDao;

    @Autowired
    private AdminMapper adminDao;

    @Autowired
    private UserAttacheMentMapper userAttacheMentDao;



    private static final String USER_LOGIN_TYPE = LoginType.USER.toString();
    private static final String ADMIN_LOGIN_TYPE = LoginType.ADMIN.toString();

    @Override
    public User getUsers(Integer userId) {

        User user = userDao.selectByPrimaryKey(userId);

        if(user == null){
            throw new BusinessException(BaseCallResultCode.DB_SELECT_ERROR);
        }
         return user;
    }

    @Override
    public CallResult<UserInfoModel>  login(String userName,String password) {
        // 1.获取用户信息
        User user = userDao.selectByPhone(userName);

        UserInfoModel userInfoModel = new UserInfoModel();

        if (user == null) {
            // 用户不存在
            CallResult<UserInfoModel> callResult = new CallResult<>(BaseCallResultCode.LOGINUSERNAMR_ERROR);
            return callResult;
        }
        if (!user.getPassword().equals(getMD5(password))) {

            CallResult<UserInfoModel> callResult = new CallResult<>(BaseCallResultCode.LOGINPASSWORD_ERROR);
            return new CallResult<>(callResult);

        }


        CustomizedToken token = new CustomizedToken(userName, getMD5(password),USER_LOGIN_TYPE);
        shirologin(token);
        userInfoModel.setId(user.getId());
        userInfoModel.setName(user.getUserName());
        userInfoModel.setPhone(user.getPhone());

        return new CallResult<>(userInfoModel);

    }
    public void  shirologin( CustomizedToken token) {
        Subject currentUser = SecurityUtils.getSubject();
        // 如果用户没登录，则走调用登录方法，否则直接跳转到成功页面
        if (!currentUser.isAuthenticated() ) {
            token.setRememberMe(false);
            try {
                // 调用login方法，登录，
                currentUser.login(token);
            } catch (AuthenticationException ae) {
                throw new BusinessException(BaseCallResultCode.LOGINPASSWORD_ERROR);
            }
        }
    }

    public CallResult<RegisterResult> register(RegisterParam param){
        String phone = param.getPhone();
        String code =  (String) EhCacheUtil.getInstance().get("ehcache", phone);
        if(code != null) {
            if (code.equals(param.getCode())) {
                User userInfo = userDao.selectByPhone(phone);
                if (userInfo == null) {
                    User user = new User();
                    user.setUserName(param.getUsername());
                    user.setPassword(getMD5(param.getPassword()));
                    user.setPhone(param.getPhone());
                    int a = userDao.insertSelective(user);
                    if (a != 1) {
                        throw new BusinessException(BaseCallResultCode.DB_INSERT_ERROR);
                    }
                    User newUser = userDao.selectByPhone(phone);
                    if (newUser != null) {
                        UserAttacheMent userAttacheMent = new UserAttacheMent();
                        userAttacheMent.setUserId(newUser.getId());
                        int i = userAttacheMentDao.insert(userAttacheMent);
                        if (a != 1) {
                            throw new BusinessException(BaseCallResultCode.DB_INSERT_ERROR);
                        }
                    }
                } else {
                    throw new BusinessException(BaseCallResultCode.DB_DUPLICATEKEY_ERROR, "当前用户已存在");
                }
            } else {
                throw new BusinessException(BaseCallResultCode.VALIDATION_CODE_ERROR);
            }
        }else{
            throw new BusinessException(BaseCallResultCode.SYSTEM_ERROR, "验证码已失效");
        }
        return new CallResult<>();
    }

    public CallResult<Boolean> changePassword(PasswordParam param){
        String phone = param.getPhone();
        String password = param.getOldPassword();
        String newPassword = param.getNewPassword();
        int id = param.getId();

        User userInfo = userDao.selectByPhone(phone);
        if(userInfo ==  null) {
            throw new BusinessException(BaseCallResultCode.LOGINUSERNAMR_ERROR);
        } else {
            if (getMD5(password).equals(userInfo.getPassword())) {
                userInfo.setPassword(getMD5(newPassword));
                int i = userDao.updateByPrimaryKey(userInfo);
            } else {
                throw new BusinessException(BaseCallResultCode.LOGINPASSWORD_ERROR);
            }
        }
        return new CallResult<>();
    }

//    public CallResult<Boolean> updateUserInfo(UserAttacheMentParam param){
//        UserAttacheMent userInfo = new UserAttacheMent();
//        userInfo.setUserId(param.getUserId());
//        userInfo.setAddress(param.getAddress());
//        userInfo.setIdentity(param.getIdentity());
//        userInfo.setHeadImage(param.getHeadImage());
//        userInfo.setSex(param.getSex());
//        int i = userAttacheMentDao.updateByUserId(userInfo);
//        if(i != 1){
//            throw new BusinessException(BaseCallResultCode.DB_UPDATE_ERROR);
//        }
//        return new CallResult<>();
//    }

    @Override
    public CallResult<Boolean> getCaptcha(CodeParam param) {
        Captcha captchaType = param.getType();
        String phone = param.getPhone();

        User userInfo = userDao.selectByPhone(phone);
        if (userInfo == null) {
            Random random = new Random();
            String code = String.valueOf(random.nextInt(9000) + 1000);

            SendMessagesUtil sendMessagesUtil = new SendMessagesUtil();

            switch (captchaType) {
                case REGISTER:
                    EhCacheUtil.getInstance().put("ehcache",phone,code);
                    sendMessagesUtil.getRequest(phone, "69997", code);
                    return new CallResult<>(true);
                case FINDPSW:
                    return new CallResult<>(true);
            }
        }else {
            throw new BusinessException(BaseCallResultCode.LOGINUSERNAMR_ERROR, "用户已存在");
        }
        return  new CallResult<>(false);
    }

    @Override
    public CallResult<AdminInfoModel> adminLogin(String adminName,String password) {
        // 1.获取用户信息
        Admin admin = adminDao.selectByName(adminName);

        AdminInfoModel adminInfoModel = new AdminInfoModel();

        if (admin == null) {
            // 用户不存在
            CallResult<AdminInfoModel> callResult = new CallResult<>(BaseCallResultCode.LOGINUSERNAMR_ERROR);
            return callResult;
        }
        if (!admin.getPassword().equals(getMD5(password))) {

            CallResult<AdminInfoModel> callResult = new CallResult<>(BaseCallResultCode.LOGINPASSWORD_ERROR);
            return callResult;

        }
        CustomizedToken token = new CustomizedToken(adminName, getMD5(password),ADMIN_LOGIN_TYPE);
        shirologin( token);
        adminInfoModel.setId(admin.getId());
        adminInfoModel.setName(admin.getUserName());
        adminInfoModel.setRole(admin.getUserRole());

        return new CallResult<>(adminInfoModel);

    }
}

