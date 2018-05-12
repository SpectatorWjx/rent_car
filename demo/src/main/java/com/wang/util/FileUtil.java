package com.wang.util;

import com.wang.common.BaseCallResultCode;
import com.wang.common.exception.BusinessException;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * author wang
 *
 */
public class FileUtil{

    //文件上传工具类服务类
    public static void uploadFile(byte[] file, String filePath, String fileName) {

        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            FileOutputStream out = new FileOutputStream(filePath+fileName);
            out.write(file);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new BusinessException(BaseCallResultCode.SYSTEM_ERROR,"上传图片失败");
        }
    }

    //图片格式输出
    public static void getImage(String path, String name, HttpServletResponse response) throws IOException {
        //利用生成的字符串构建图片
        String url = "D:/image/"+path+"/"+name;
        File file = new File(url);
        if(!file.exists()){
            throw new BusinessException(BaseCallResultCode.SYSTEM_ERROR,"图片不存在");
        }
        String imgType = StringUtils.substringAfterLast(name,".");

        FileInputStream imgInput = new FileInputStream(url);
        BufferedImage bufferedImage = ImageIO.read(imgInput);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bufferedImage, imgType, out);
        out.flush();
        imgInput.close();
        out.close();
    }
}