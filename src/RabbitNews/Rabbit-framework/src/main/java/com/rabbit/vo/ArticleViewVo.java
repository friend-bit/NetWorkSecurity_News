package com.rabbit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleViewVo {
    //评论数
    private String date;
    //访问量
    private Integer viewCount;
}
