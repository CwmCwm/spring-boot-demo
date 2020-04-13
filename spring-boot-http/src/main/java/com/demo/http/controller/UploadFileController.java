package com.demo.http.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


/**
 * springMVC上传文件
 * */
@Controller
public class UploadFileController {

    /**
     * @Value 读取application.properties配置文件对应的值并赋值到属性上
     * */
    @Value(value = "${upload.directory}")
    private String uploadDirectory;

    /**
     * 这个是单文件上传
     * 使用PostMan工具测试，关于postman的使用百度就好了
     * postman设置
     * 请求头
     *   Content-Type=multipart/form-data
     * 请求体
     *   file=选择文件
     * */
    @RequestMapping(value = "/uploadFileController/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new NullPointerException("空指针异常，文件为空");
        }

        byte[] bytes = file.getBytes();
        Path path = Paths.get(uploadDirectory + file.getOriginalFilename());
        Files.write(path, bytes);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        return responseMap;
    }


    /**
     * 这个是多文件上传，前端如下，就是多个input元素使用同名，这里就是 name="files"
     * <input type="file" name="files"/>
     * <input type="file" name="files"/>
     *
     * postman设置
     * 请求头
     *   Content-Type=multipart/form-data
     * 请求体
     *   files=选择文件
     *   files=选择文件
     * */
    @RequestMapping(value = "/uploadFileController/uploadFiles", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
        if (files.length == 0) {
            throw new NullPointerException("空指针异常，文件为空");
        }

        if (files.length > 0) {
            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(uploadDirectory + file.getOriginalFilename());
                Files.write(path, bytes);
            }
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        return responseMap;
    }



    /**
     这个是单文件上传，文件的二进制流直接存入数据库，数据库字段类型 blob相关类型
     使用PostMan工具测试，关于postman的使用百度就好了
     postman设置
     请求头
       Content-Type=multipart/form-data
     请求体
       file=选择文件
     * */
    @RequestMapping(value = "/uploadFileController/uploadIntoDB", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadIntoDB(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new NullPointerException("空指针异常，文件为空");
        }

        byte[] bytes = file.getBytes();
        //这里直接插入数据库，mybatis操作。当然这里省略

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("errorCode", 0);
        responseMap.put("message", "success");
        return responseMap;
    }

    /**
     从数据库读取文件的二进制流，将文件传输给前端，这里就看http支持的 Content-Type 和你的要求
     * */
    @RequestMapping(value = "/uploadFileController/downloadFile", method = RequestMethod.POST)
    @ResponseBody
    public void downloadFile(HttpServletResponse httpServletResponse) throws IOException {
        //这里从数据库中获取文件的二进制流，mybatis操作。当然这里省略
        byte[] bytes = "从数据库获取文件的二进制流".getBytes();

        //这里设置返回的 Content-Type 为 image/jpeg  图片类型，这样浏览器可以直接显示图片
        //当然其他文件，如excel， csv 等等你就找对应的 Content-Type
        httpServletResponse.setContentType("image/jpeg");
        httpServletResponse.setCharacterEncoding("UTF-8");
        OutputStream outputStream = httpServletResponse.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();

        //图片还有另一种方式，就是base64编码为字符串返回，这个就很简单了，自己百度咯
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("errorCode", 0);
//        responseMap.put("message", "success");
//        return responseMap;
    }

}
