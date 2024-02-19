package com.rabbit.controller;

import com.rabbit.result.ResponseResult;
import com.rabbit.service.ArticleService;
import com.rabbit.service.SensitiveCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SensitiveCategory")
public class SensitiveCategoryController {

    @Autowired
    private SensitiveCategoryService sensitiveCategoryService;

    @GetMapping("/sensitiveCategoryList")
    public ResponseResult sensitiveCategoryList(Integer pageNum, Integer pageSize){
        ResponseResult result=sensitiveCategoryService.sensitiveCategoryList(pageNum,pageSize);
        return  result;
    }
}
