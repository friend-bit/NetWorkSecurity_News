package com.rabbit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.entity.Article;
import com.rabbit.entity.CNVD;
import com.rabbit.entity.Category;
import com.rabbit.entity.Sensitive;
import com.rabbit.exception.SystemException;
import com.rabbit.mapper.CNVDMapper;
import com.rabbit.result.ResponseResult;
import com.rabbit.result.enums.AppHttpCodeEnum;
import com.rabbit.service.CNVDService;
import com.rabbit.utils.BeanCopyUtils;
import com.rabbit.utils.RedisCache;
import com.rabbit.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class CNVDServiceImpl extends ServiceImpl<CNVDMapper, CNVD> implements CNVDService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult getcnvdList(Integer pageNum, Integer pageSize, Long cveid, String serial_number) {
        LambdaQueryWrapper<CNVD> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(Objects.nonNull(cveid)&&cveid>0, CNVD::getId,cveid);
        queryWrapper.like(Objects.nonNull(serial_number)&&serial_number.length()>0,CNVD::getSerialNumber,serial_number);

        Page<CNVD> page=new  Page(pageNum,pageSize);
        page(page,queryWrapper);
        List<CNVD> cnvds = page.getRecords();

        PageVo pageVo = new PageVo(cnvds,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addCNVD(CNVD cnvd) {
        if(!StringUtils.hasText(cnvd.getSerialNumber())){
            throw new SystemException(AppHttpCodeEnum.CNVDSerialNumber_NOT_NULL);
        }
        if(!StringUtils.hasText(cnvd.getSummary())){
            throw new SystemException(AppHttpCodeEnum.CNVDSummary_NOT_NULL);
        }
        if(!StringUtils.hasText(cnvd.getLevel())){
            throw new SystemException(AppHttpCodeEnum.CNVDLevel_NOT_NULL);
        }
        if(!StringUtils.hasText(cnvd.getLink())){
            throw new SystemException(AppHttpCodeEnum.CNVDLink_NOT_NULL);
        }
        if(!StringUtils.hasText(cnvd.getPatch())){
            throw new SystemException(AppHttpCodeEnum.CNVDPath_NOT_NULL);
        }
        CNVD cnvds= BeanCopyUtils.copyBean(cnvd,CNVD.class);
        save(cnvds);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delCNVD(Long id) {
        LambdaQueryWrapper<CNVD> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CNVD::getId,id);
        remove(lambdaQueryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult geCNVDDetail(Long id) {
        LambdaQueryWrapper<CNVD> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CNVD::getId,id);
        List<CNVD> CNVDs=list(lambdaQueryWrapper);
        return ResponseResult.okResult(CNVDs);
    }

    @Override
    public ResponseResult updateCNVD(CNVD cnvd) {
        updateById(cnvd);
        return ResponseResult.okResult();
    }
}
