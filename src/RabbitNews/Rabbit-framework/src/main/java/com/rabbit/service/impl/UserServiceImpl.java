package com.rabbit.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.constants.SystemConstants;
import com.rabbit.entity.Article;
import com.rabbit.entity.Category;
import com.rabbit.entity.User;
import com.rabbit.exception.SystemException;
import com.rabbit.mapper.UserMapper;
import com.rabbit.result.ResponseResult;
import com.rabbit.result.enums.AppHttpCodeEnum;
import com.rabbit.service.UserService;
import com.rabbit.utils.BeanCopyUtils;
import com.rabbit.utils.SecurityUtils;
import com.rabbit.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;
    @Override
    public ResponseResult userInfo() {
        Long userId = SecurityUtils.getUserId();
        User id = getById(userId);
        UserInfoVo userInfoVo= BeanCopyUtils.copyBean(id,UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        update(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult regedit(User user) {
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
//        if(!StringUtils.hasText(user.getEmail())){
//            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
//        }

        if(userNameExist(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }

        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        save(user);

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult userList(Integer pageNum,Integer pageSize,Long userId,String nickName) {
        LambdaQueryWrapper<User> userqueryWrapper=new LambdaQueryWrapper<>();
        userqueryWrapper.eq(Objects.nonNull(userId)&&userId>0,User::getId,userId);
        userqueryWrapper.eq(Objects.nonNull(nickName)&&nickName.length()>0,User::getNickName,nickName);
        userqueryWrapper.eq(User::getUserType, SystemConstants.ARTICLE_STATUS_DRAFT);

        Page<User> page=new Page(pageNum,pageSize);
        page(page,userqueryWrapper);

        List<UserInfoVo> userVos = BeanCopyUtils.copyBeanList(page.getRecords(), UserInfoVo.class);
        PageVo pageVo = new PageVo(userVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult delUser(Long id) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,id);
        remove(lambdaQueryWrapper);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult searchUser(Long id) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,id);
        List<User> userList=list(lambdaQueryWrapper);
        List<UserInfoVo> userInfoVoList = userList.stream()
                .map(user -> {
                    UserInfoVo userInfoVo = new UserInfoVo();
                    userInfoVo.setId(user.getId());
                    userInfoVo.setNickName(user.getNickName());
                    userInfoVo.setSex(user.getSex());
                    userInfoVo.setAvatar(user.getAvatar());
                    userInfoVo.setEmail(user.getEmail());
                    userInfoVo.setPhonenumber(user.getPhonenumber());
                    return userInfoVo;
                })
                .collect(Collectors.toList());
        return ResponseResult.okResult(userInfoVoList);
    }

    @Override
    public ResponseResult update(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,userName);
        return count(lambdaQueryWrapper)>0;
    }
}
