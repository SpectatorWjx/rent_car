/*
 *   阔地教育科技有限公司版权所有（codyy.com/codyy.cn ）
 *   Copyright (c) 2017, Codyy and/or its affiliates. All rights reserved.
 */
package com.wang.controller;

import com.wang.common.BaseCallResultCode;
import com.wang.common.CallResult;
import com.wang.result.NoShiroResult;
import com.wang.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangjiaxiang
 * @date 2018/3/22.
 */
//@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class OtherController {

    @GetMapping(value = "/noShiro")
    @ResponseBody
    public CallResult<NoShiroResult> getResult() {
        CallResult<NoShiroResult> callResult = new CallResult<>(BaseCallResultCode.API_PERMISSION_ERROR);
        return callResult;
    }

    @GetMapping(value="/picture/{path}/{name}")
    public ModelAndView getCaptchaCode(@PathVariable String path, @PathVariable String name, HttpServletResponse response) throws IOException {
        FileUtil.getImage(path,name, response);
        return null;
    }
}

