package com.rabbit.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.entity.ArticleTag;
import com.rabbit.mapper.ArticleTagMapper;
import com.rabbit.service.ArticleTagService;
import com.rabbit.vo.ArticleViewVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTagImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {

    @Override
    public List<ArticleViewVo> getCountByTimeRange(String timeRange, int i) {
        return null;
    }
}
