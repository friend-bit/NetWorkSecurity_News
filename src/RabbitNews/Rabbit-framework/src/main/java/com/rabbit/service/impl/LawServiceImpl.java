package com.rabbit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.entity.Law;
import com.rabbit.mapper.LawMapper;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.LawService;
import com.rabbit.utils.BeanCopyUtils;
import com.rabbit.vo.LawVo;
import com.rabbit.vo.PageVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LawServiceImpl extends ServiceImpl<LawMapper, Law> implements LawService {

    @Override
    public ResponseResult lawList(Integer pageNum, Integer pageSize, Long lawId, String lawTitle) {
        LambdaQueryWrapper<Law> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Objects.nonNull(lawId)&&lawId>0, Law::getId,lawId);
        lambdaQueryWrapper.eq(Objects.nonNull(lawTitle)&&lawTitle.length()>0,Law::getFilename,lawTitle);
        //状态是正式发布的

        //分页查询
        Page<Law> page=new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

//        List<Law> laws = page.getRecords();
        List<LawVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), LawVo.class);


        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);

    }

    @Override
    public ResponseResult lawSecure(Integer pageNum, Integer pageSize, Long lawId, String lawTitle) {
        LambdaQueryWrapper<Law> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Objects.nonNull(lawId)&&lawId>0, Law::getId,lawId);
        lambdaQueryWrapper.eq(Objects.nonNull(lawTitle)&&lawTitle.length()>0,Law::getFilename,lawTitle);
        //状态是正式发布的
        lambdaQueryWrapper.eq(Law::getType,10);
        //分页查询
        Page<Law> page=new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

//        List<Law> laws = page.getRecords();
        List<LawVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), LawVo.class);


        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult lawDevelop(Integer pageNum, Integer pageSize, Long lawId, String lawTitle) {
        LambdaQueryWrapper<Law> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Objects.nonNull(lawId)&&lawId>0, Law::getId,lawId);
        lambdaQueryWrapper.eq(Objects.nonNull(lawTitle)&&lawTitle.length()>0,Law::getFilename,lawTitle);
        //状态是正式发布的
        lambdaQueryWrapper.eq(Law::getType,11);
        //分页查询
        Page<Law> page=new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

//        List<Law> laws = page.getRecords();
        List<LawVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), LawVo.class);


        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

}
