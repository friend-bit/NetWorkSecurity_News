package com.rabbit.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.entity.Sensitive;
import com.rabbit.result.ResponseResult;

import java.util.List;

public interface SensitiveService extends IService<Sensitive> {
//    List<Sensitive> list(LambdaQueryWrapper<Sensitive> queryWrapper);

    ResponseResult SensitiveList(Integer pageNum, Integer pageSize, Long SensitiveId, String SensitiveContent);

    List<String> listSensitive(LambdaQueryWrapper<Sensitive> queryWrapper);

    ResponseResult addSensitive(Sensitive sensitive);

    ResponseResult delSensitive(Long id);

    ResponseResult geSensitiveDetail(Long id);
}
