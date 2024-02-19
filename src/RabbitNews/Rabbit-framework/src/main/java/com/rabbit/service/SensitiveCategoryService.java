package com.rabbit.service;

import com.rabbit.result.ResponseResult;

public interface SensitiveCategoryService {
    ResponseResult sensitiveCategoryList(Integer pageNum, Integer pageSize);
}
