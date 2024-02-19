package com.rabbit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.constants.SystemConstants;
import com.rabbit.entity.Link;
import com.rabbit.mapper.LinkMapper;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.LinkService;
import com.rabbit.utils.BeanCopyUtils;
import com.rabbit.vo.LinkVo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("LinkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {
    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Link::getStatus, SystemConstants.Link_STATUS_NORAML);
        List<Link> links=list(lambdaQueryWrapper);
        //转换vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}
