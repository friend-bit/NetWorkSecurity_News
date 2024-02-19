package com.rabbit.service;

import com.rabbit.result.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    ResponseResult uploadFile(MultipartFile multipartFile);

    ResponseResult uploadImg(MultipartFile multipartFile);
}
