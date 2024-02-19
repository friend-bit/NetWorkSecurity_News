package com.rabbit.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {
    private Long id;
    private String nickName;
    //头像
    private String avatar;
    private String sex;
    private String email;
    private String phonenumber;
    private String signature;
    private Date createTime;
    private Date updateTime;
}