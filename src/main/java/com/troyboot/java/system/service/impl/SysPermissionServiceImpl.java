package com.troyboot.java.system.service.impl;

import com.troyboot.java.common.utils.BuildTree;
import com.troyboot.java.system.dao.SysPermissionMapper;
import com.troyboot.java.system.domain.Tree;
import com.troyboot.java.system.po.SysPermission;
import com.troyboot.java.system.service.SysPermissionService;
import com.troyboot.java.system.service.dao.SysPermissionDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Authour YangYang
 * @Date 2018/6/15 11:52
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    public SysPermission getPermissionById(Long id) {
        return sysPermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tree<SysPermission>> getPermissionByUserId(Long userId) {
        List<Tree<SysPermission>> trees = new ArrayList<Tree<SysPermission>>();
        List<SysPermission> sysPermissions = sysPermissionDao.getPermissionByUserId(userId);
        for (SysPermission sysPermission : sysPermissions) {
            Tree<SysPermission> tree = new Tree<SysPermission>();
            tree.setId(sysPermission.getId().toString());
            tree.setParentId(sysPermission.getParentId().toString());
            tree.setText(sysPermission.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysPermission.getUrl());
            attributes.put("icon", sysPermission.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<SysPermission>> list = BuildTree.buildList(trees, "0");
        return list;
    }

    @Override
    public List<SysPermission> getPermissionByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<SysPermission> list() {
        List<SysPermission> permissions = sysPermissionDao.list();
        return permissions;
    }

    @Override
    public List<SysPermission> getAll() {
        List<SysPermission> permissions = sysPermissionDao.getAll();
        return permissions;
    }

    @Override
    public Set<String> listPerms(Long userId) {
        List<String> perms = sysPermissionDao.getPersByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

}
