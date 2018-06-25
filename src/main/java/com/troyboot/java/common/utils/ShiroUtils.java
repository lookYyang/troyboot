package com.troyboot.java.common.utils;

import com.troyboot.java.system.po.SysUser;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.lang.reflect.InvocationTargetException;


public class ShiroUtils{

    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static SysUser getUser() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipal() == null){
            return null;
        }
        Object object = subject.getPrincipal();
        SysUser userPo = new SysUser();
        try {
            PropertyUtils.copyProperties(userPo,object);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return userPo;
    }

    public static void logout() {
        getSubject().logout();
    }

}
