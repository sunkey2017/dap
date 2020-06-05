package com.longi.dap.service.impl;

import com.longi.dap.config.ServerConfig;
import com.longi.dap.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadServiceImpl implements IUploadService {

    // 文件上传地址
    private static String UPLOAD_PATH = "Station/images/upload";

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 保存图片
     *
     * @param file
     * @param request
     * @return
     */

    @Transactional
    @Override
    public Map saveImg(MultipartFile file, HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        if (file != null && !"".equals(file)) {
            String name = java.net.URLDecoder.decode(file.getOriginalFilename());
            //获取最后一个.的位置
            int lastIndexOf = name.lastIndexOf(".");
            //获取文件的后缀名 .jpg
            String suffix = name.substring(lastIndexOf);
            String newname= UUID.randomUUID()+suffix;
            System.out.println(newname);

            InputStream inputStream = file.getInputStream();
            Path directory = Paths.get(UPLOAD_PATH);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            Files.copy(inputStream, directory.resolve(newname));
            String url = serverConfig.getUrl();
            String imgUrl = url + "/upload/getImg" + "/" + newname;
            System.out.println(imgUrl);
            map.put("code","200");
            map.put("imgUrl", imgUrl);
        } else {
            map.put("code","500");
            map.put("imgUrl", "");
        }
        return map;
    }
}
