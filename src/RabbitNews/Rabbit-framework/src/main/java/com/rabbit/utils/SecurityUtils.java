package com.rabbit.utils;

import com.rabbit.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static LoginUser getLoginUser(){
        return (LoginUser) getAuthentication().getPrincipal();
    }

    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Long getUserId(){
        return getLoginUser().getUser().getId();
    }

    public static boolean isAdmin() {
        Long id=getLoginUser().getUser().getId();
        return id!=null && id.equals(1L);
    }
}
