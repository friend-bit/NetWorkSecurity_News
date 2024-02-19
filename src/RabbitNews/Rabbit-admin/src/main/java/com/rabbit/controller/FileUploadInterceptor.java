package com.rabbit.controller;


import cn.hutool.core.io.FileUtil;
import com.rabbit.result.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
public class FileUploadInterceptor {

    @Value("${ip:localhost}")
    String ip;

    @Value("${server.port}")
    String port;

    private static final String ROOT_PATH=System.getProperty("user.dir")+File.separator+"files";
    @PostMapping("/upload")
    public ResponseResult upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String mainName= FileUtil.mainName(originalFilename);
        String extName=FileUtil.extName(originalFilename);
        if(!FileUtil.exist(ROOT_PATH)){
            FileUtil.mkdir(ROOT_PATH);
        }
        if(FileUtil.exist(ROOT_PATH+File.separator+originalFilename)){
            originalFilename=mainName+"."+extName;
        }
        File saveFile=new File(ROOT_PATH+File.separator+originalFilename);
        file.transferTo(saveFile);
        String url="http://"+ip+":"+port+"/file/download/"+originalFilename;
        return ResponseResult.okResult(url);
    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
        String filePath=ROOT_PATH + File.separator+fileName;
        if(!FileUtil.exist(filePath)){
            return;
        }
        byte[] bytes=FileUtil.readBytes(filePath);
        FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
}
