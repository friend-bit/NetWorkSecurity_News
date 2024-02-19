package com.rabbit.controller;

import com.rabbit.dto.TagListDto;
import com.rabbit.entity.Tag;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.TagService;
import com.rabbit.vo.PageVo;
import com.rabbit.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(){
        return ResponseResult.okResult(tagService.list());
    }

    @GetMapping("/searchTag")
    public ResponseResult<PageVo> searchTag(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.searchTag(pageNum,pageSize,tagListDto);
    }


    @PostMapping("addTag")
    public ResponseResult addTag(@RequestBody Tag tag){
        return tagService.addTag(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delTag(@PathVariable("id") Long id){
        return tagService.delTag(id);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list=tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
}
