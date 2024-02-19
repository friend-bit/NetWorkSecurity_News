package com.rabbit.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "rabbit_hackevent")
public class Hackevent {
    @TableId
    private Long id;

    private String title;

    private String content;

    private String type;

    private String summary;

    private String author;

    private Long categoryId;

    private String thumbnail;

    private String isTop;

    private String status;

    private Integer commentCount;

    private Long viewCount;

    private String isComment;
    
    private Long createBy;
    
    private Date createdataTime;
    
    private Long updateBy;
    
    private Date updateTime;

    private Integer delFlag;

}

