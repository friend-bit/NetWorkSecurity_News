package com.rabbit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.dto.TagListDto;
import com.rabbit.entity.Tag;
import com.rabbit.mapper.TagMapper;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.TagService;
import com.rabbit.utils.BeanCopyUtils;
import com.rabbit.vo.PageVo;
import com.rabbit.vo.TagVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo> searchTag(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //分页查询
        LambdaQueryWrapper<Tag> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getRemark,tagListDto.getRemark());

        Page<Tag> page=new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        PageVo pageVo=new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(Tag tag) {

        return null;
    }

    @Override
    public ResponseResult delTag(Long id) {

        return null;
    }

    @Override
    public List<TagVo> listAllTag() {
        LambdaQueryWrapper<Tag> wrapper=new LambdaQueryWrapper<>();
        wrapper.select(Tag::getId,Tag::getName);
        List<Tag> list=list(wrapper);
        List<TagVo> tagVos= BeanCopyUtils.copyBeanList(list,TagVo.class);
        return tagVos;
    }
}
