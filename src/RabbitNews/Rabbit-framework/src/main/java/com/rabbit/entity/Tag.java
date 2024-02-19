package com.rabbit.entity;

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
@TableName("rabbit_tag")
public class Tag  {
    @TableId
    private Long id;
    
    private String name;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    
    private Integer delFlag;
    
    private String remark;
}

