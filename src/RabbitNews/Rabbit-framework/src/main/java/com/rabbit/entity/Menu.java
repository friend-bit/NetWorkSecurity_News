package com.rabbit.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rabbit.vo.MenuVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("sys_menu")
@Accessors(chain = true)
public class Menu {
    @TableId
    private Long id;

    private String menuName;

    private Long parentId;

    private Integer orderNum;

    private String path;

    private String component;

    private Integer isFrame;

    private String menuType;

    private String visible;

    private String status;

    private String perms;

    private String icon;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;
    
    private String remark;
    
    private String delFlag;
    @TableField(exist = false)
    private List<Menu> children;
}

