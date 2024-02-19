package com.rabbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.dto.AddArticleDto;
import com.rabbit.entity.Article;
import com.rabbit.result.ResponseResult;
import com.rabbit.vo.ArticleViewVo;

import java.util.List;


public interface ArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long newsId,String newsTitle);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);


    ResponseResult searchArticle(Long id);

    ResponseResult update(Article article);

    ResponseResult contentExamine(Integer pageNum, Integer pageSize, Long newsId, String newsTitle);


    ResponseResult delArticle(Long id);


    ResponseResult examineContent(Long id);


    ResponseResult searchNews(Integer pageNum, Integer pageSize, String newsTitle);

    ResponseResult lineCharts(String timeRange);

    ResponseResult hackArticle(Integer pageNum, Integer pageSize, Long newsId, String newsTitle);

    ResponseResult TopArticle();

    ResponseResult CarouselChart();

    ResponseResult latestArticle();

    ResponseResult TechnologyArticle(Integer pageNum, Integer pageSize, Long newsId, String newsTitle);
//
//    List<ArticleViewVo> getCountByTimeRange(String timeRange, int i);
}

