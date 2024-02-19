package com.rabbit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbit.entity.Article;
import com.rabbit.vo.ArticleViewVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ArticleMapper extends BaseMapper<Article> {
    List<ArticleViewVo> getCountByTimeRange(@Param("timeRange") String timeRange);
}

