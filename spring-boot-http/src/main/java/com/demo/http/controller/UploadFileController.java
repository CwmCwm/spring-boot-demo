package com.demo.http.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

}
