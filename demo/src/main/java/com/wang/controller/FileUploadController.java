package com.wang.controller;



import com.wang.common.CallResult;
import com.wang.util.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileUploadController {


        //处理文件上传
        @PostMapping(value="/uploadimg")
        public @ResponseBody CallResult<Boolean> uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
            String fileName = file.getOriginalFilename();
            String filePath = "D:/image/head/";
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            String imgUrl = "/head/"+fileName;
            System.out.println("存入数据库:"+imgUrl);
            return new CallResult<>(true);
        }

}