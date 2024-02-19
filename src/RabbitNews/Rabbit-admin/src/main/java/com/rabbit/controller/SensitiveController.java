package com.rabbit.controller;

import com.rabbit.dto.AddArticleDto;
import com.rabbit.entity.Sensitive;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.CategoryService;
import com.rabbit.service.SensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensitive")
public class SensitiveController {
    @Autowired
    private SensitiveService sensitiveService;

    @GetMapping("/SensitiveList")
    public ResponseResult SensitiveList(Integer pageNum, Integer pageSize, Long SensitiveId, String SensitiveContent){
        return sensitiveService.SensitiveList(pageNum,pageSize,SensitiveId,SensitiveContent);
    }

    @PostMapping("/addSensitive")
    public ResponseResult addSensitive(@RequestBody Sensitive sensitive){
        return sensitiveService.addSensitive(sensitive);
    }
    @DeleteMapping("/{id}")
    public ResponseResult delSensitive(@PathVariable("id") Long id){
        return sensitiveService.delSensitive(id);
    }

    @GetMapping("/{id}")
    public ResponseResult geSensitiveDetail(@PathVariable("id")Long id){
        return sensitiveService.geSensitiveDetail(id);
    }
}
