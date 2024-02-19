package com.rabbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.entity.Article;
import com.rabbit.entity.ArticleTag;
import com.rabbit.vo.ArticleViewVo;

import java.util.List;

public interface ArticleTagService extends IService<ArticleTag> {
    List<ArticleViewVo> getCountByTimeRange(String timeRange, int i);
}
