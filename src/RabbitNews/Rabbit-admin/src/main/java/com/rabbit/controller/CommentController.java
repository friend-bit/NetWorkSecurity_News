package com.rabbit.controller;

import com.rabbit.result.ResponseResult;
import com.rabbit.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/commenttExamine")
    private ResponseResult commenttExamine(Integer pageNum,Integer pageSize,Long commentId,String commentTitle){
        return commentService.commenttExamine(pageNum,pageSize,commentId,commentTitle);
    }
    @PutMapping("/examineContent/{id}")
    public ResponseResult examineContent(@PathVariable("id") Long id){
        return commentService.examineContent(id);
    }


}
