package com.rabbit.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@AllArgsConstructor
@Data
@NoArgsConstructor
@TableName("rabbit_pdf")
public class Law {
    @TableId
    private Integer id;
    
    private String filename;
    
    private String url;

    private String type;
}

