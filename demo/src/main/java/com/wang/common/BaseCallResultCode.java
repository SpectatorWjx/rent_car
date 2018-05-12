/*
 *   阔地教育科技有限公司版权所有（codyy.com/codyy.cn ）
 *   Copyright (c) 2017, Codyy and/or its affiliates. All rights reserved.
 */
package com.wang.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjiaxiang
 * @date 2018/3/20.
 */
public enum BaseCallResultCode implements IBaseCallResultCode {

    SUCCESS("000000", "success"),

    //系统错误
    SYSTEM_ERROR("200000", "系统错误"),
    //系统错误- 一般性错误(60029****)
    SYSTEM_BUSY("200001", "系统繁忙请稍后再试"),
    API_PERMISSION_ERROR("200004", "该接口无权限访问"),


    //系统错误-未知错误(600290000)
    UNKNOWN_ERROR("300000", "未知错误"),




    //登录(60091****)
    LOGINPASSWORD_ERROR("400001", "密码错误"),
    LOGINUSERNAMR_ERROR("400002", "用户不存在"),
    VALIDATION_CODE_ERROR("400003", "验证码错误"),
    API_ACCESS_ERROR("400004", "登陆时间过长，请重新登陆"),

    //系统错误-数据库错误(60021****)
    DB_SELECT_ERROR("500001", "数据库查询数据失败"),
    DB_INSERT_ERROR("500002", "数据库插入数据失败."),
    DB_UPDATE_ERROR("500003", "数据库更新数据失败."),
    DB_DELETE_ERROR("500004", "数据库删除数据失败."),
    DB_ACCESS_ERROR("500005", "数据库访问异常"),
    DB_DATA_ERROR("500006","数据库数据错误"),
    DB_DUPLICATEKEY_ERROR("500007", "数据库字段数据重复添加"),

    //客户端错误-数据格式错误(60053****)
    REQUEST_PARAMS_WRONG("600001", "请求参数不合法"),
    LOST_REQUEST_PARAMS("600002", "参数不能为空");




    /**
     * 返回错误码
     **/
    private String status;
    /**
     * 返回错误信息
     **/
    private String message;

    private static Map<String, BaseCallResultCode> map = new HashMap<String, BaseCallResultCode>();
    static {
        map.put(SUCCESS.status, SUCCESS);
    }

    BaseCallResultCode(String status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getStatus() {
        return status;
    }

    /**
     * Return call result enum according to status code.
     *
     * @param status specified status code.
     * @return
     */
    public static IBaseCallResultCode byStatus(String status) {
        if (status=="") {
            return null;
        }

        for(IBaseCallResultCode e: values()) {
            if (e.getStatus().equals(status)) {
                return e;
            }
        }
        return null;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


