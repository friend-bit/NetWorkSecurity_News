package com.rabbit.service.impl;

import com.rabbit.entity.LoginUser;
import com.rabbit.entity.User;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.LoginService;
import com.rabbit.utils.JwtUtil;
import com.rabbit.utils.RedisCache;
import com.rabbit.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate ;
        if(Objects.isNull(authenticate= authenticationManager.authenticate(authenticationToken))){
            throw new RuntimeException("用户名或密码错误");
        }
        //生成token
        LoginUser loginUser=(LoginUser)authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //用户信息存入redis
        redisCache.setCacheObject("adminlogin:"+userId,loginUser);

        Map<String,String>map = new HashMap<>();
        map.put("token",jwt);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser=(LoginUser)authentication.getPrincipal();
        Long userId = SecurityUtils.getUserId();
        redisCache.deleteObject("adminlogin"+loginUser);
        return ResponseResult.okResult();
    }


}

