package com.rabbit.controller;

import com.rabbit.dto.AddArticleDto;
import com.rabbit.entity.Article;
import com.rabbit.entity.User;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
    }

    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long newsId,String newsTitle){
        ResponseResult result=articleService.articleList(pageNum,pageSize,newsId,newsTitle);
        return  result;
    }
    @GetMapping("/contentExamine")
    public ResponseResult contentExamine(Integer pageNum,Integer pageSize,Long newsId,String newsTitle){
        ResponseResult result=articleService.contentExamine(pageNum,pageSize,newsId,newsTitle);
        return  result;
    }
    @GetMapping("/searchArticle/{id}")
    public ResponseResult searchArticle(@PathVariable("id") Long id) {
        return articleService.searchArticle(id);
    }

    @PutMapping("/update")
    public ResponseResult update(@RequestBody Article article){
        return articleService.update(article);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delArticle(@PathVariable("id") Long id){
        return articleService.delArticle(id);
    }

    @PutMapping("/examineContent/{id}")
    public ResponseResult examineContent(@PathVariable("id") Long id){
        return articleService.examineContent(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id")Long id){
        return articleService.getArticleDetail(id);
    }

    @GetMapping("/lineCharts/{timeRange}")
    public ResponseResult lineCharts(@PathVariable String timeRange){
        return articleService.lineCharts(timeRange);
    }
}
