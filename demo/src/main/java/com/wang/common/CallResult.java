/*
 *   阔地教育科技有限公司版权所有（codyy.com/codyy.cn ）
 *   Copyright (c) 2017, Codyy and/or its affiliates. All rights reserved.
 */
package com.wang.common;

import java.io.Serializable;

/**
 * @author wangjiaxiang
 * @date 2018/3/20.
 */
public class CallResult<R> implements Serializable, IBaseCallResultCode {

    /**
     * 返回提示信息
     */
    protected String message = BaseCallResultCode.SUCCESS.getMessage();
    /**
     * 返回错误码
     */
    protected String status = BaseCallResultCode.SUCCESS.getStatus();

    /**
     * 返回结果对象
     */
    protected R result;


    /**
     * Constructor.
     */
    public CallResult() {
    }

    public CallResult(R returnValue) {
        this.result = returnValue;
    }

    public CallResult(IBaseCallResultCode callResultCode) {
        this.status = callResultCode.getStatus();
        this.message = callResultCode.getMessage();
    }

    public CallResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

//    /**
//     * Create call result instance with return value.
//     *
//     * @param t
//     * @param <T>
//     * @return
//     */
//    public static <T> CallResult<T> newInstance(T t) {
//        return new CallResult<>(t);
//    }
//
//    /**
//     * Create call result instance with {@link BaseCallResultCode#SUCCESS} value.
//     *
//     * @param <T>
//     * @return
//     */
//    public static <T> CallResult<T> newInstance() {
//        return new CallResult<>(BaseCallResultCode.SUCCESS);
//    }
//
//    /**
//     * Create default instance with result enum value.
//     *
//     * @param baseCalllResultEnum
//     * @param <T>
//     * @return
//     */
//    public static <T> CallResult<T> newInstance(IBaseCallResultCode baseCalllResultEnum) {
//        return new CallResult<>(baseCalllResultEnum);
//    }
//
//    /**
//     * Create default instance with result enum value and throwable.
//     *
//     * @param baseCalllResultEnum
//     * @param e
//     * @param <T>
//     * @return
//     */
//    public static <T> CallResult<T> newInstance(IBaseCallResultCode baseCalllResultEnum, Throwable e) {
//        return newInstance(baseCalllResultEnum, e, null);
//    }
//
//    /**
//     * Create default instance with result enum value and message.
//     *
//     * @param baseCalllResultEnum
//     * @param message
//     * @param <T>
//     * @return
//     */
//    public static <T> CallResult<T> newInstance(IBaseCallResultCode baseCalllResultEnum, String message) {
//        CallResult<T> result = new CallResult<>(baseCalllResultEnum);
//        result.setMessage(message);
//        return result;
//    }
//
//    /**
//     * Create default instance with result enum value and throwable and message.
//     *
//     * @param baseCalllResultEnum
//     * @param e
//     * @param message
//     * @param <T>
//     * @return
//     */
//    public static <T> CallResult<T> newInstance(IBaseCallResultCode baseCalllResultEnum, Throwable e, String message) {
//        CallResult<T> result = new CallResult<>(baseCalllResultEnum);
//      //  result.setException(e);
//        result.setMessage(message);
//        return result;
//    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }


    /**
     * Check if result is successful.
     *
     * @return <code>true</code> if result is successful.
     */
    public boolean isSuccessful() {
        return BaseCallResultCode.byStatus(this.status) == BaseCallResultCode.SUCCESS;
    }

    @Override
    public String toString() {

        return "CallResult{" + "message='" + message + '\'' + ", status='" + status + '\'' + ", result=" + result
                +'}';
    }
}

