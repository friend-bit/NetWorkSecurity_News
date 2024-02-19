package com.rabbit.controller;


import com.rabbit.annotation.SystemLog;
import com.rabbit.entity.User;
import com.rabbit.result.ResponseResult;
import com.rabbit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    @PostMapping("/updateUserInfo")
    @SystemLog(businessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    @PostMapping("/regedit")
    public ResponseResult regedit(@RequestBody User user){
        return userService.regedit(user);
    }
}
