package com.longi.dap.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IUploadService {

    Map saveImg(MultipartFile file, HttpServletRequest request) throws Exception;



}
