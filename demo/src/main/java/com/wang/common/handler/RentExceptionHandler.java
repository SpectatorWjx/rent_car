/*
 *   阔地教育科技有限公司版权所有（codyy.com/codyy.cn ）
 *   Copyright (c) 2017, Codyy and/or its affiliates. All rights reserved.
 */
package com.wang.common.handler;

import com.wang.common.BaseCallResultCode;
import com.wang.common.CallResult;
import com.wang.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangjiaxiang
 * @date 2018/3/20.
 */
@ControllerAdvice(annotations = Controller.class)
public class RentExceptionHandler {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(RentExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CallResult handle(Exception e) {
        if (e instanceof BusinessException) {
            LOGGER.warn("业务异常", e);
            return new CallResult(((BusinessException) e).getStatus(), e.getMessage());
        }

        if (e instanceof NullPointerException) {
            LOGGER.error("空指针异常", e);
            return new CallResult(BaseCallResultCode.UNKNOWN_ERROR.getStatus(), BaseCallResultCode.UNKNOWN_ERROR.getMessage());
        }

        LOGGER.error("system error find", e);
        return new CallResult(BaseCallResultCode.SYSTEM_ERROR);
    }

}
