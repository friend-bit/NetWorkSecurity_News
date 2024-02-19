package com.rabbit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbit.entity.Role;
import com.rabbit.mapper.RoleMapper;
import com.rabbit.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        if(id == 1L){
            List<String> roleKeys=new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }else{
            return getBaseMapper().selectRoleKeyUserId(id);
        }
    }
}
