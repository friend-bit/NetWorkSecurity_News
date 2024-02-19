package com.rabbit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.entity.Sensitive;
import com.rabbit.entity.SensitiveCategory;
import com.rabbit.mapper.SensitiveCategoryMapper;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.SensitiveCategoryService;
import com.rabbit.vo.PageVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensitiveCategoryServiceImpl extends ServiceImpl<SensitiveCategoryMapper, SensitiveCategory> implements SensitiveCategoryService {

    @Override
    public ResponseResult sensitiveCategoryList(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SensitiveCategory> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        Page<SensitiveCategory> page=new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);
        List<SensitiveCategory> sensitives = page.getRecords();

        PageVo pageVo = new PageVo(sensitives,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }
}
