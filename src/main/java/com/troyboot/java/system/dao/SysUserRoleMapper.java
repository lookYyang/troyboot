package com.troyboot.java.system.dao;

import com.troyboot.java.system.po.SysUserRole;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
}