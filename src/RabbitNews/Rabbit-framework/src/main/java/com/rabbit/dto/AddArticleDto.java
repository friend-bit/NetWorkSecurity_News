package com.rabbit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddArticleDto {
    private Long id;

    private String title;

    private String content;

    private String summary;

    private Long categoryId;

    private String type;

    private String thumbnail;

    private String isTop;

    private  String status;

    private Long ViewCount;

    private String isComment;

    private List<Long> tags;
}
