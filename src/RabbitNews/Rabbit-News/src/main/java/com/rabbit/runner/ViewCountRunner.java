package com.rabbit.runner;

import com.rabbit.entity.Article;
import com.rabbit.mapper.ArticleMapper;
import com.rabbit.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询 id viewCount
        List<Article> articles=articleMapper.selectList(null);
        Map<String, Integer> viewCountMap = articles.stream()
                .filter(article -> article.getId() != null && article.getViewCount() != null)
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> {
                    return article.getViewCount().intValue();
                }));
        //存储redis
        redisCache.setCacheMap("article:viewCount",viewCountMap);
    }
}
