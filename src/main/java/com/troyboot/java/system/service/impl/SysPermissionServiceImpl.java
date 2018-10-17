package com.troyboot.java.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.troyboot.java.common.utils.BuildTree;
import com.troyboot.java.common.utils.PageUtils;
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
    public SysPermission getPermissionById(int id) {
        return sysPermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tree<SysPermission>> getPermissionByUserId(int userId) {
        List<Tree<SysPermission>> trees = new ArrayList<Tree<SysPermission>>();
        List<SysPermission> sysPermissions = sysPermissionDao.getPermissionByUserId(userId);
        for (SysPermission sysPermission : sysPermissions) {
            Tree<SysPermission> tree = new Tree<SysPermission>();
            tree.setId(sysPermission.getId().toString());
            tree.setParentId(sysPermission.getParentId().toString());
            tree.setTitle(sysPermission.getName());
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
    public List<SysPermission> getPermissionByRoleId(int roleId) {
        return null;
    }

    @Override
    public PageUtils list(Map<String, Object> params) {
        int limit = "".equals(params.get("limit")) ? 1 : Integer.valueOf(params.get("limit").toString());
        int offset = "".equals(params.get("offset")) ? 15 : Integer.valueOf(params.get("offset").toString());
        PageHelper.startPage(limit, offset, true);
        List<SysPermission> sysPermissions = sysPermissionDao.selectAll();
        PageInfo<SysPermission> pageInfo = new PageInfo<>(sysPermissions);
        PageUtils pageUtils = new PageUtils(sysPermissions, pageInfo.getTotal());
        return pageUtils;
    }

    @Override
    public List<SysPermission> selectAll() {
        List<SysPermission> permissionList = sysPermissionDao.list();
        return permissionList;
    }

    @Override
    public Set<String> listPerms(int userId) {
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
