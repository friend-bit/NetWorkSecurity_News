package com.rabbit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.constants.SystemConstants;
import com.rabbit.entity.LoginUser;
import com.rabbit.entity.Menu;
import com.rabbit.mapper.MenuMapper;
import com.rabbit.service.MenuService;
import com.rabbit.utils.SecurityUtils;
import com.rabbit.vo.MenuVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus=null;
        if(SecurityUtils.isAdmin()){
            menus=menuMapper.selectAllRouterMenu();

        }else{
            menus=menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        //构建tree
        List<Menu> menuTree=builderMenuTree(menus,0L);
        return menuTree;
    }

    @Override
    public List<String> selectPermsByUserId(Long id) {
        if(id == 1L){
            LambdaQueryWrapper<Menu> wrapper=new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.Menu,SystemConstants.Button);
            wrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORAML);
            List<Menu> menuList = list(wrapper);
            List<String> perms = menuList.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        return getBaseMapper().selectPermsByUserId(id);
    }

    private List<Menu> builderMenuTree(List<Menu> menus, long parentId) {
        List<Menu> menuTree = menus.stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> menu.setChildren(getChildren(menu,menus)))
                .collect(Collectors.toList());
        return menuTree;
    }

    private List<Menu> getChildren(Menu menu, List<Menu> menus) {
        List<Menu> childrenList=menus.stream()
                .filter(m->m.getParentId().equals(menu.getId()))
                .map(m->m.setChildren(getChildren(m,menus)))
                .collect(Collectors.toList());
        return childrenList;
    }
}
