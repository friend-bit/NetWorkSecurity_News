package com.rabbit.controller;

import com.rabbit.entity.Article;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/article")
public class ArticleController{

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        ResponseResult result=articleService.hotArticleList();
        return result;
    }
    @GetMapping("/CarouselChart")
    public ResponseResult CarouselChart(){
        ResponseResult result=articleService.CarouselChart();
        return result;
    }

    @GetMapping("/latestArticle")
    public ResponseResult latestArticle(){
        ResponseResult result=articleService.latestArticle();
        return result;
    }

    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id")Long id){
        return articleService.updateViewCount(id);
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long newsId,String newsTitle){
        ResponseResult result=articleService.articleList(pageNum,pageSize,newsId,newsTitle);
        return  result;
    }

    @GetMapping("/TopArticle")
    public ResponseResult TopArticle(){
        ResponseResult result=articleService.TopArticle();
        return  result;
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id")Long id){
        return articleService.getArticleDetail(id);
    }

    @GetMapping("/searchNews")
    public ResponseResult searchNews(Integer pageNum,Integer pageSize,String newsTitle){
        return articleService.searchNews(pageNum,pageSize,newsTitle);
    }

    @GetMapping("/hackArticle")
    public ResponseResult hackArticle(Integer pageNum,Integer pageSize,Long newsId,String newsTitle){
        ResponseResult result=articleService.hackArticle(pageNum,pageSize,newsId,newsTitle);
        return  result;
    }

    @GetMapping("/TechnologyArticle")
    public ResponseResult TechnologyArticle(Integer pageNum,Integer pageSize,Long newsId,String newsTitle){
        ResponseResult result=articleService.TechnologyArticle(pageNum,pageSize,newsId,newsTitle);
        return  result;
    }
}

