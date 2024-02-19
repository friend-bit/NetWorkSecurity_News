package com.rabbit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.entity.LoginUser;
import com.rabbit.entity.Menu;
import com.rabbit.vo.MenuVo;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<String> selectPermsByUserId(Long id);
}
