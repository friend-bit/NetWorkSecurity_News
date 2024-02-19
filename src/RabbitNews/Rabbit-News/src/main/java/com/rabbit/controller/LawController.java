package com.rabbit.controller;

import com.rabbit.result.ResponseResult;
import com.rabbit.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/law")
public class LawController {

    @Autowired
    public LawService lawService;

    @GetMapping("/lawList")
    public ResponseResult lawList(Integer pageNum,Integer pageSize,Long lawId,String lawTitle){
        return lawService.lawList(pageNum,pageSize,lawId,lawTitle);
    }
    @GetMapping("/lawSecure")
    public ResponseResult lawSecure(Integer pageNum,Integer pageSize,Long lawId,String lawTitle){
        return lawService.lawSecure(pageNum,pageSize,lawId,lawTitle);
    }
    @GetMapping("/lawDevelop")
    public ResponseResult lawDevelop(Integer pageNum,Integer pageSize,Long lawId,String lawTitle){
        return lawService.lawDevelop(pageNum,pageSize,lawId,lawTitle);
    }
}
