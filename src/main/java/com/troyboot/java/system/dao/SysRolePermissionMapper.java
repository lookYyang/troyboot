package com.troyboot.java.system.dao;

import com.troyboot.java.system.po.SysRolePermission;

public interface SysRolePermissionMapper {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}