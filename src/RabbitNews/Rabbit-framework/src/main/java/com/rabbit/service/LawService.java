package com.rabbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.entity.Law;
import com.rabbit.result.ResponseResult;

public interface LawService extends IService<Law> {
    ResponseResult lawList(Integer pageNum, Integer pageSize, Long lawId, String lawTitle);

    ResponseResult lawSecure(Integer pageNum, Integer pageSize, Long lawId, String lawTitle);

    ResponseResult lawDevelop(Integer pageNum, Integer pageSize, Long lawId, String lawTitle);
}
