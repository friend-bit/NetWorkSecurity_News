package com.rabbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.entity.Comment;
import com.rabbit.result.ResponseResult;

public interface CommentService extends IService<Comment> {
    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);

    ResponseResult commenttExamine(Integer pageNum, Integer pageSize, Long commentId, String commentTitle);

    ResponseResult examineContent(Long id);
}
