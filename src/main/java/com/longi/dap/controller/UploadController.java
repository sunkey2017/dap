package com.longi.dap.controller;


import com.longi.dap.service.IPowerStationBaseService;
import com.longi.dap.service.IUploadService;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    //文件上传地址
    private static String UPLOAD_PATH = "Station/images/upload";

    @RequestMapping("/img")
    public String upload(@RequestParam("fileList") MultipartFile file, HttpServletRequest request) {
        String url ="";
        try {
         Map map =  uploadService.saveImg(file, request);
        if ("200".equals(map.get("code"))) {
            url = map.get("imgUrl").toString();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 读取图片
     *
     * @param response
     * @param name
     * @throws Exception
     */
    @GetMapping("/getImg/{name}")
    public void getImage(HttpServletResponse response, @PathVariable(name = "name", required = true)String name) throws Exception {
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(Files.readAllBytes(Paths.get(UPLOAD_PATH).resolve(name)));
        outputStream.flush();
        outputStream.close();
    }


}
