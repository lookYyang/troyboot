package com.troyboot.java.system.service;

import com.troyboot.java.common.utils.PageUtils;
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

    PageUtils list(Map<String, Object> params);

    List<SysPermission> selectAll();

    Set<String> listPerms(Long id);
}
