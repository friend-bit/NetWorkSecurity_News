package com.rabbit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.constants.SystemConstants;
import com.rabbit.entity.Article;
import com.rabbit.entity.Category;
import com.rabbit.mapper.CategoryMapper;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.ArticleService;
import com.rabbit.service.CategoryService;
import com.rabbit.utils.BeanCopyUtils;
import com.rabbit.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("CategoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表，状态为已发布的文章
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper=new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORAML);
        List<Article> articleList =articleService.list(articleLambdaQueryWrapper);
        //获取文章分类id,并且其重
        Set<Long> categoryIds=articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);

        categories.stream()
                .filter(category -> SystemConstants.STATUS_NORAML.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);



        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus,SystemConstants.NORMAL);
        List<Category> list=list(wrapper);
        List<CategoryVo> categoryVos=BeanCopyUtils.copyBeanList(list,CategoryVo.class);
        return categoryVos;
    }
}
