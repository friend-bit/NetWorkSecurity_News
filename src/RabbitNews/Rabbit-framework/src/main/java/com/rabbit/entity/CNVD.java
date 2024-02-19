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
@TableName("rabbit_cnvd")
public class CNVD {
    @TableId
    private Integer id;
    
    private String serialNumber;
    
    private String title;
    
    private String summary;
    
    private String level;
    
    private String link;
    
    private String patch;
    
    private String viewCount;
    
    private Date releaseTime;

}

