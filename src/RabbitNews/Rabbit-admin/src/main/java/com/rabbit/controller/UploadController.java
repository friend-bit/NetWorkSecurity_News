package com.rabbit.controller;

import com.rabbit.result.ResponseResult;
import com.rabbit.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadFile(MultipartFile multipartFile){
        return uploadService.uploadFile(multipartFile);
    }
}