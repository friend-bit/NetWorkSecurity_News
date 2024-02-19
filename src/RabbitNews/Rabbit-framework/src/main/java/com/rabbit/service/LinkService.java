package com.rabbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.entity.Link;
import com.rabbit.result.ResponseResult;

public interface LinkService extends IService<Link> {
    ResponseResult getAllLink();
}
