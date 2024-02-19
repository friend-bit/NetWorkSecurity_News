package com.rabbit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.constants.SystemConstants;
import com.rabbit.entity.Comment;
import com.rabbit.entity.Sensitive;
import com.rabbit.exception.SystemException;
import com.rabbit.mapper.CommentMapper;
import com.rabbit.result.ResponseResult;
import com.rabbit.result.enums.AppHttpCodeEnum;
import com.rabbit.service.CommentService;
import com.rabbit.service.SensitiveService;
import com.rabbit.service.UserService;
import com.rabbit.utils.BeanCopyUtils;
import com.rabbit.vo.CommentVo;
import com.rabbit.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private SensitiveService sensitiveService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        //对articleId进行判断
        lambdaQueryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);
        //根评论 rootId为-1
        lambdaQueryWrapper.eq(Comment::getRootId,-1);

        lambdaQueryWrapper.eq(Comment::getType,commentType);
        //分页查询
        Page<Comment> page=new Page(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        List<CommentVo> commentVoList=toCommentVoList(page.getRecords());

        //查询所有根评论对应的子评论集合，并且赋值对应的属性
        for (CommentVo commentVo : commentVoList) {
            //查询对应的字评论
            List<CommentVo> children=getChildren(commentVo.getId());
            //赋值
            commentVo.setChildren(children);
        }
        return ResponseResult.okResult(new PageVo(commentVoList,page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        // 获取敏感词列表
        List<String> sensitiveWords = getSensitiveWordsFromDatabase();

        // 判断评论内容是否包含敏感词
        if (isSensitive(comment.getContent(), sensitiveWords)) {
            throw new SystemException(AppHttpCodeEnum.SENSITIVE_CONTENT);
        }
        save(comment);
        return ResponseResult.okResult();
    }

    private List<String> getSensitiveWordsFromDatabase() {
        try {
            // 使用LambdaQueryWrapper构建查询条件
            LambdaQueryWrapper<Sensitive> queryWrapper = new LambdaQueryWrapper<>();

            if (queryWrapper == null) {
                // 如果为空，返回空的敏感词列表
                return Collections.emptyList();
            }

            // 直接返回敏感词列表
            List<Sensitive> sensitiveList = sensitiveService.list(queryWrapper);

            if (sensitiveList == null) {
                // 如果查询结果为空，返回空的敏感词列表
                return Collections.emptyList();
            }

            List<String> result = new ArrayList<>();
            for (Sensitive sensitive : sensitiveList) {
                result.add(sensitive.getContent());
            }

            return result;
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
            throw new RuntimeException("Error retrieving sensitive content", e);
        }
    }

//    private List<String> getSensitiveWordsFromDatabase() {
//        // 使用LambdaQueryWrapper构建查询条件
//        LambdaQueryWrapper<Sensitive> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.select(Sensitive::getContent);
//
//        // 执行查询
//        List<Sensitive> sensitiveWordList = sensitiveService.listSensitive(queryWrapper);
//
//        // 使用增强型for循环遍历查询结果，将敏感词添加到列表中
//        List<String> sensitiveWords = new ArrayList<>();
//        for (Sensitive sensitiveWord : sensitiveWordList) {
//            sensitiveWords.add(sensitiveWord.getContent());
//        }
//
//        return sensitiveWords;
//    }

    private boolean isSensitive(String content, List<String> sensitiveWords) {
        // 将评论内容转换为小写，方便匹配
        String lowercaseContent = content.toLowerCase();

        // 遍历敏感词列表，判断评论内容中是否包含敏感词
        for (String word : sensitiveWords) {
            if (lowercaseContent.contains(word)) {
                return true; // 包含敏感词，返回true
            }
        }

        return false; // 不包含敏感词，返回false
    }
    @Override
    public ResponseResult commenttExamine(Integer pageNum, Integer pageSize, Long commentId, String commentTitle) {
        LambdaQueryWrapper<Comment> queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.eq(Comment::getStatus, SystemConstants.ARTICLE_STATUS_DRAFT);

        Page<Comment> page=new  Page(pageNum,pageSize);
        page(page,queryWrapper);
        List<Comment> comments = page.getRecords();
        return ResponseResult.okResult(comments);
    }

    @Override
    public ResponseResult examineContent(Long id) {
        LambdaUpdateWrapper<Comment> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Comment::getId, id)
                .set(Comment::getStatus, 0);
        this.update(lambdaUpdateWrapper);
        return ResponseResult.okResult();
    }

    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历Vo集合
        for (CommentVo commentVo : commentVos) {
            //通过createBy查询用户用户昵称并赋值
            String nickName = userService.getById(commentVo.getUserId()).getNickName();
            String avatar=userService.getById(commentVo.getUserId()).getAvatar();
            commentVo.setUsername(nickName);
            commentVo.setAvatar(avatar);
            //通过toCommentUserId查询用户的昵称并赋值
            //toCommentUserId不为-1才进行查询
            if (commentVo.getToCommentUserId()!=-1){
                String toComenetUsername = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toComenetUsername);
            }
        }
        return commentVos;
    }


    private List<CommentVo> getChildren(Long id){
        LambdaQueryWrapper<Comment> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Comment::getRootId,id);
        lambdaQueryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> comments=list(lambdaQueryWrapper);
        List<CommentVo> commentVos = toCommentVoList(comments);
        return commentVos;
    }
}
