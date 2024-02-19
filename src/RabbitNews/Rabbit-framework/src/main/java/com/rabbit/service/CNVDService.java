package com.rabbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.entity.CNVD;
import com.rabbit.result.ResponseResult;


public interface CNVDService extends IService<CNVD> {
    ResponseResult getcnvdList(Integer pageNum, Integer pageSize, Long cveid, String serial_number);

    ResponseResult addCNVD(CNVD cnvd);

    ResponseResult delCNVD(Long id);

    ResponseResult geCNVDDetail(Long id);

    ResponseResult updateCNVD(CNVD cnvd);
}
