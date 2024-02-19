package com.rabbit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("sensitive_category")
public class SensitiveCategory{
    
    private Integer id;

    private String name;

    private String description;

    private String status;

    private Integer defFlag;

}

