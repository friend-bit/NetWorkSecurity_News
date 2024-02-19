package com.rabbit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("sensitive_tag")
public class SensitiveTag {
    
    private Integer sensitiveId;
    
    private Integer sensitiveCategoryId;

}

