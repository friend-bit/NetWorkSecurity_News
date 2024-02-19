package com.rabbit.service.impl;

import com.rabbit.exception.SystemException;
import com.rabbit.result.ResponseResult;
import com.rabbit.result.enums.AppHttpCodeEnum;
import com.rabbit.service.UploadService;
import com.rabbit.utils.OSSUtils;
import com.rabbit.utils.PathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("UploadService")
public class UploadServiceImpl implements UploadService {

    @Autowired
    private OSSUtils ossUtils;
    @Override
    public ResponseResult uploadFile(MultipartFile multipartFile) {
        //TODO 判断文件类型或文件大小
        String originalFilename = multipartFile.getOriginalFilename();
//        if(!originalFilename.endsWith(".png")&&!originalFilename.endsWith(".jpg")){
//            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_NULL);
//        }
        //上传到OSS
        String filePath = PathUtils.gengrateFilePath(originalFilename);
        String url=null;
        try {
            url=ossUtils.uploadOSS(multipartFile,filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.okResult(url);
    }

    @Override
    public ResponseResult uploadImg(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        if(!originalFilename.endsWith(".png")&&!originalFilename.endsWith(".jpg")){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_NULL);
        }
        //上传到OSS
        String filePath = PathUtils.gengrateFilePath(originalFilename);
        String url=null;
        try {
            url=ossUtils.uploadOSS(multipartFile,filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseResult.okResult(url);
    }
}
