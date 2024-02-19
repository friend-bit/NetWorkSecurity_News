package com.rabbit.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.constants.SystemConstants;
import com.rabbit.dto.AddArticleDto;
import com.rabbit.entity.*;
import com.rabbit.mapper.ArticleMapper;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.ArticleService;
import com.rabbit.service.ArticleTagService;
import com.rabbit.service.CategoryService;
import com.rabbit.utils.BeanCopyUtils;
import com.rabbit.utils.RedisCache;
import com.rabbit.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public ResponseResult hotArticleList() {

        //查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        //必须正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORAML);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page=new  Page(1,8);

        page(page,queryWrapper);
        List<Article> articles = page.getRecords();

        return ResponseResult.okResult(articles);

    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize,Long newsId,String newsTitle) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Objects.nonNull(newsId)&&newsId>0, Article::getId,newsId);
        lambdaQueryWrapper.eq(Objects.nonNull(newsTitle)&&newsTitle.length()>0,Article::getTitle,newsTitle);
        //状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORAML);
        //isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page=new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Article> articles = page.getRecords();
        for(Article article:articles){
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article=getById(id);
        //从redis中获取viewCount
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId=articleDetailVo.getCategoryId();
        Category category= categoryService.getById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        Article article=BeanCopyUtils.copyBean(articleDto,Article.class);
        article.setCreatedataTime(new Date());
        save(article);
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult searchArticle(Long id) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getId,id);
        List<Article> articles=list(lambdaQueryWrapper);
        for(Article article:articles){
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articles);
    }

    @Override
    public ResponseResult update(Article article) {
        updateById(article);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult contentExamine(Integer pageNum, Integer pageSize, Long newsId, String newsTitle) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Objects.nonNull(newsId)&&newsId>0, Article::getId,newsId);
        lambdaQueryWrapper.eq(Objects.nonNull(newsTitle)&&newsTitle.length()>0,Article::getTitle,newsTitle);
        //状态是正式发布的;
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_DRAFT);

        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page=new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Article> articles = page.getRecords();
        for(Article article:articles){
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }

//        articles.stream()
//                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
//                .collect(Collectors.toList());
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult delArticle(Long id) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Article::getId,id);
        remove(lambdaQueryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult examineContent(Long id) {
        LambdaUpdateWrapper<Article> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Article::getId, id)
                .set(Article::getStatus, 0);
        this.update(lambdaUpdateWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult searchNews(Integer pageNum, Integer pageSize,String newsTitle) {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Article::getTitle,newsTitle);
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORAML);
        Page<Article> page=new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Article> articles = page.getRecords();
        for(Article article:articles){
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult lineCharts(String timeRange) {
        Map<String,Object> map=new HashMap<>();
        Date today=new Date();
        List<DateTime> dateRange;
        switch (timeRange){
            case "week":
                dateRange= DateUtil.rangeToList(DateUtil.offsetDay(today,-6),today, DateField.DAY_OF_WEEK);
                break;
            case "month":
                dateRange= DateUtil.rangeToList(DateUtil.offsetDay(today,-29),today, DateField.DAY_OF_MONTH);
                break;
            case "month2":
                dateRange= DateUtil.rangeToList(DateUtil.offsetDay(today,-59),today, DateField.DAY_OF_MONTH);
                break;
            case "month3":
                dateRange= DateUtil.rangeToList(DateUtil.offsetDay(today,-89),today, DateField.DAY_OF_MONTH);
                break;
            default:
                dateRange=new ArrayList<>();
        }
        List<String> dateStrRange=datetimeToDateStr(dateRange);
        map.put("date",dateStrRange);
        List<ArticleViewVo> releaseCount=articleMapper.getCountByTimeRange(timeRange);
        map.put("release",countList(releaseCount,dateStrRange));
        return ResponseResult.okResult(map);
    }

    private List<String> datetimeToDateStr(List<DateTime> dateTimesList){
        List<String> list= CollUtil.newArrayList();
        if(CollUtil.isEmpty(dateTimesList)){
            return list;
        }
        for(DateTime dateTime:dateTimesList){
            String date=DateUtil.formatDate(dateTime);
            list.add(date);
        }
        return list;
    }

    private List<Integer> countList(List<ArticleViewVo> ArticleViewVoList,List<String> dateRange){
        List<Integer> list=CollUtil.newArrayList();
        if(CollUtil.isEmpty(ArticleViewVoList)){
            return list;
        }
        for(String date:dateRange){
            Integer count=ArticleViewVoList.stream().filter(articleViewVo -> date.equals(articleViewVo.getDate()))
                    .map(ArticleViewVo::getViewCount).findFirst().orElse(0);
            list.add(count);
        }
        return list;
    }

    @Override
    public ResponseResult hackArticle(Integer pageNum, Integer pageSize, Long newsId, String newsTitle) {
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORAML);
        queryWrapper.eq(Article::getCategoryId,3);
        Page<Article> page=new  Page(pageNum,pageSize);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();
        return ResponseResult.okResult(articles);
    }

    @Override
    public ResponseResult TopArticle() {
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        //必须正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORAML);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page=new Page(1,3);

        page(page,queryWrapper);
        List<Article> articles = page.getRecords();

        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);

        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult CarouselChart() {
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        //必须正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORAML);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page=new Page(2,4);

        page(page,queryWrapper);
        List<Article> articles = page.getRecords();

        return ResponseResult.okResult(articles);
    }

    @Override
    public ResponseResult latestArticle() {
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        //必须正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORAML);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getCreatedataTime);
        //最多只查询10条
        Page<Article> page=new Page(1,15);

        page(page,queryWrapper);
        List<Article> articles = page.getRecords();

        for(Article article:articles){
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(articleListVos);
    }

    @Override
    public ResponseResult TechnologyArticle(Integer pageNum, Integer pageSize, Long newsId, String newsTitle) {
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORAML);
        queryWrapper.eq(Article::getCategoryId,1);
        Page<Article> page=new  Page(pageNum,pageSize);
        page(page,queryWrapper);
        List<Article> articles = page.getRecords();
        return ResponseResult.okResult(articles);
    }

}

