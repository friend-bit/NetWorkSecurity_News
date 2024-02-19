package com.rabbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.entity.User;
import com.rabbit.result.ResponseResult;

public interface UserService extends IService<User> {
    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult regedit(User user);

    ResponseResult userList(Integer pageNum,Integer pageSize,Long userId,String nickName);

    ResponseResult delUser(Long id);

    ResponseResult searchUser(Long id);

    ResponseResult update(User user);
}
