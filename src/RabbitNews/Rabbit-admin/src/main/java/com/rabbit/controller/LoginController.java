package com.rabbit.controller;

import com.rabbit.entity.LoginUser;
import com.rabbit.entity.Menu;
import com.rabbit.entity.User;
import com.rabbit.exception.SystemException;
import com.rabbit.result.ResponseResult;
import com.rabbit.result.enums.AppHttpCodeEnum;
import com.rabbit.service.LoginService;
import com.rabbit.service.MenuService;
import com.rabbit.service.RoleService;
import com.rabbit.service.UserService;
import com.rabbit.utils.BeanCopyUtils;
import com.rabbit.utils.RedisCache;
import com.rabbit.utils.SecurityUtils;
import com.rabbit.vo.AdminUserInfoVo;
import com.rabbit.vo.MenuVo;
import com.rabbit.vo.RoutersVo;
import com.rabbit.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }


    @PostMapping("/user/regedit")
    public ResponseResult regedit(@RequestBody User user){
        return userService.regedit(user);
    }

    @GetMapping("getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms=menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList=roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
        //获取用户信息
        User user=loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装数据返回
        AdminUserInfoVo adminUserInfoVo=new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);

    }

    @GetMapping("getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        //获取当前登录的用户
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<Menu> menus=menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
