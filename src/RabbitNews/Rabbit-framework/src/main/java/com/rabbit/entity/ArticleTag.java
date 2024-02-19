package com.rabbit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "rabbit_article_tag")
public class ArticleTag implements Serializable {

    private Long articleId;

    private Long tagId;


}
