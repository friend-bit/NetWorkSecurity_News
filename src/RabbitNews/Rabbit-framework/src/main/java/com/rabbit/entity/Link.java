package com.rabbit.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * (Link)表实体类
 *
 * @author makejava
 * @since 2023-09-25 22:54:27
 */
@SuppressWarnings("serial")
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("rabbit_link")
public class Link {
    @TableId
    private Long id;

    private String name;

    private String logo;

    private String description;
//网站地址
    private String address;
//审核状态（0代表审核通过，1代表审核未通过，2代表未审核）
    private String status;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
//删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;




}

