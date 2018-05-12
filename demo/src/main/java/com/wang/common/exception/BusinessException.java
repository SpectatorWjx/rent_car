/*
 *   阔地教育科技有限公司版权所有（codyy.com/codyy.cn ）
 *   Copyright (c) 2017, Codyy and/or its affiliates. All rights reserved.
 */
package com.wang.common.exception;

import com.wang.common.IBaseCallResultCode;

/**
 * @author wangjiaxiang
 * @date 2018/3/20.
 */
public class BusinessException extends RuntimeException implements IBaseCallResultCode {


    /**
     * 返回错误码
     **/
    private String status;
    /**
     * 返回错误信息
     **/
    private String message;

    /**
     * 异常信息提示 (枚举)
     */
    private IBaseCallResultCode callResultCode;

    public BusinessException(IBaseCallResultCode callResultCode) {
        this.callResultCode = callResultCode;
        this.status = callResultCode.getStatus();
        this.message = callResultCode.getMessage();
    }

    public BusinessException(IBaseCallResultCode callResultCode, String message) {
        this.callResultCode = callResultCode;
        this.status = callResultCode.getStatus();
        this.message = message;
    }

    public BusinessException(String status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getStatus() {
        return status;
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

    public IBaseCallResultCode getCallResultCode() {
        return callResultCode;
    }

    public void setCallResultCode(IBaseCallResultCode callResultCode) {
        this.callResultCode = callResultCode;
    }


    @Override
    public String toString() {

        return "BusinessException{" + "status='" + status + '\'' + ", message='" + message + '\'' + ", callResultCode=" + callResultCode + '}';
    }
}


