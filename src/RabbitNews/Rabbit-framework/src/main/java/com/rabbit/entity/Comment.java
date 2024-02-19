package com.rabbit.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("rabbit_comment")
public class Comment  {
    @TableId
    private Long id;

    private String type;

    private Long articleId;

    private Long rootId;

    private Long userId;
    //状态（0已发布，1草稿）
    private String status;

    private String content;

    private Long toCommentUserId;

    private Long toCommentId;
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

//    private Long isLiked;

    private Integer delFlag;
}

