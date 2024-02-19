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
@TableName(value = "sys_user")
public class User  {

    @TableId
    private Long id;

    private String userName;

    private String nickName;

    private String password;

    private String status;

    private String email;

    private String phonenumber;

    private String sex;

    private String avatar;

    private String signature;

    private String userType;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer delFlag;
}

