package com.rabbit.controller;


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

    @GetMapping("/userList")
    public ResponseResult userList(Integer pageNum,Integer pageSize,Long userId,String nickName){
        return userService.userList(pageNum,pageSize,userId,nickName);
    }


    @GetMapping("/searchUser/{id}")
    public ResponseResult searchUser(@PathVariable("id") Long id){
        return userService.searchUser(id);
    }
    @DeleteMapping("/{id}")
    public ResponseResult delUser(@PathVariable("id") Long id){
        return userService.delUser(id);
    }
    @PutMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }
    @PutMapping("/update")
    public ResponseResult update(@RequestBody User user){
        return userService.update(user);
    }
}
