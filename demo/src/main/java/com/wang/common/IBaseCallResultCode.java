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
public interface IBaseCallResultCode extends Serializable {

    /**
     * Return status code.
     *
     * @return
     */
    String getStatus();

    /**
     * Return description of the status.
     *
     * @return
     */
    String getMessage();
}

