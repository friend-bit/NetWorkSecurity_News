package com.rabbit.service;

import com.rabbit.entity.User;
import com.rabbit.result.ResponseResult;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
