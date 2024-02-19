package com.rabbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.entity.Category;
import com.rabbit.result.ResponseResult;
import com.rabbit.vo.CategoryVo;

import java.util.List;

public interface CategoryService extends IService<Category> {
    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();
}
