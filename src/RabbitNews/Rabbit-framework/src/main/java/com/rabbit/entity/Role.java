package com.rabbit.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("sys_role")
public class Role implements Serializable {
    @TableId
    private Long id;
    
    private String roleName;
    
    private String roleKey;
    
    private String roleSort;
    
    private String status;
    
    private String delFlag;
    
    private String createBy;
    
    private Date createTime;
    
    private Date updateBy;
    
    private Date updateTime;
    
    private String remark;

}

