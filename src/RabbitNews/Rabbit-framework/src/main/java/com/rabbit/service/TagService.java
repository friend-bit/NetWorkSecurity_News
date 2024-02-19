package com.rabbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.dto.TagListDto;
import com.rabbit.entity.Tag;
import com.rabbit.result.ResponseResult;
import com.rabbit.vo.PageVo;
import com.rabbit.vo.TagVo;

import java.util.List;

public interface TagService extends IService<Tag> {


    ResponseResult<PageVo> searchTag(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    ResponseResult addTag(Tag tag);

    ResponseResult delTag(Long id);

    List<TagVo> listAllTag();
}
