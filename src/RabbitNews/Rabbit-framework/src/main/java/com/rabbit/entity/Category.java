package com.rabbit.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("rabbit_category")
public class Category {

    private Long id;
//分类名
    private String name;
//父分类id
    private Long pid;
//描述
    private String description;
//状态0：正常，1禁用
    private String status;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
//删除标志（0代表未删除）
    private Integer defFlag;




}

