package com.rabbit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.entity.*;
import com.rabbit.mapper.SensitiveMapper;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.SensitiveCategoryService;
import com.rabbit.service.SensitiveService;
import com.rabbit.utils.BeanCopyUtils;
import com.rabbit.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SensitiveServiceImpl extends ServiceImpl<SensitiveMapper, Sensitive> implements SensitiveService {

    @Autowired
    private SensitiveService sensitiveService;

    @Autowired
    private SensitiveCategoryService sensitiveCategoryService;

//    @Override
//    public List<Sensitive> list(LambdaQueryWrapper<Sensitive> queryWrapper) {
//        return null;
//    }

    @Override
    public ResponseResult SensitiveList(Integer pageNum, Integer pageSize, Long SensitiveId, String SensitiveContent) {
        LambdaQueryWrapper<Sensitive> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Objects.nonNull(SensitiveId)&&SensitiveId>0, Sensitive::getId,SensitiveId);
        lambdaQueryWrapper.like(Objects.nonNull(SensitiveContent)&&SensitiveContent.length()>0,Sensitive::getContent,SensitiveContent);

        Page<Sensitive> page=new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Sensitive> sensitives = page.getRecords();

        PageVo pageVo = new PageVo(sensitives,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public List<String> listSensitive(LambdaQueryWrapper<Sensitive> queryWrapper) {
        List<Sensitive> sensitiveList = sensitiveService.list(queryWrapper);
        List<String> result = sensitiveList.stream()
                .map(Sensitive::getContent)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public ResponseResult addSensitive(Sensitive sensitiveDto) {
        Sensitive sensitive= BeanCopyUtils.copyBean(sensitiveDto,Sensitive.class);
        save(sensitive);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delSensitive(Long id) {
        LambdaQueryWrapper<Sensitive> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Sensitive::getId,id);
        remove(lambdaQueryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult geSensitiveDetail(Long id) {
        LambdaQueryWrapper<Sensitive> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Sensitive::getId,id);
        List<Sensitive> sensitives=list(lambdaQueryWrapper);
        return ResponseResult.okResult(sensitives);
    }
}
