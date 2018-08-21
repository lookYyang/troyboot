package com.troyboot.java.system.service;

import com.troyboot.java.system.domain.Tree;
import com.troyboot.java.system.po.SysPermission;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Authour YangYang
 * @Date 2018/6/15 11:46
 */
@Service
public interface SysPermissionService {

    SysPermission getPermissionById(Long id);

    List<Tree<SysPermission>> getPermissionByUserId(Long id);

    List<SysPermission> getPermissionByRoleId(Long id);

    List<SysPermission> list();

    List<SysPermission> getAll(Map<String, Object> params);

    Set<String> listPerms(Long id);
}
