package com.rabbit.service;

import java.util.List;

public interface RoleService {
    List<String> selectRoleKeyByUserId(Long id);
}
