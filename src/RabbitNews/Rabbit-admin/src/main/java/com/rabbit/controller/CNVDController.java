package com.rabbit.controller;

import com.rabbit.entity.Article;
import com.rabbit.entity.CNVD;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.CNVDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cnvd")
public class CNVDController {

    @Autowired
    private CNVDService cnvdService;

    @GetMapping("/getcnvdList")
    public ResponseResult getcnvdList(Integer pageNum, Integer pageSize, Long CVEID, String serial_number){
        return cnvdService.getcnvdList(pageNum,pageSize,CVEID,serial_number);
    }
    @PostMapping("/addCNVD")
    public ResponseResult addCNVD(@RequestBody CNVD cnvd){
        return cnvdService.addCNVD(cnvd);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delCNVD(@PathVariable("id") Long id){
        return cnvdService.delCNVD(id);
    }

    @GetMapping("/{id}")
    public ResponseResult geCNVDDetail(@PathVariable("id")Long id){
        return cnvdService.geCNVDDetail(id);
    }

    @PutMapping("/updateCNVD")
    public ResponseResult updateCNVD(@RequestBody CNVD cnvd){
        return cnvdService.updateCNVD(cnvd);
    }
}
