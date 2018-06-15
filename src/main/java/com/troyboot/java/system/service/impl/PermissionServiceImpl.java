package com.troyboot.java.system.service.impl;

import com.troyboot.java.common.utils.BuildTree;
import com.troyboot.java.system.dao.PermissionDao;
import com.troyboot.java.system.dao.RoleDao;
import com.troyboot.java.system.domain.Tree;
import com.troyboot.java.system.po.PermissionPo;
import com.troyboot.java.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	PermissionDao permissionDao;
	@Autowired
	RoleDao roleDao;


	@Override
	public Tree<PermissionPo> getPermissionTree(Long id) {
		List<Tree<PermissionPo>> trees = new ArrayList<Tree<PermissionPo>>();
		List<PermissionPo> permissionPos = permissionDao.findListByUserId(id);
		for (PermissionPo permissionPo : permissionPos) {
			Tree<PermissionPo> tree = new Tree<PermissionPo>();
			tree.setId(permissionPo.getId().toString());
			tree.setParentId(permissionPo.getParent_id().toString());
			tree.setText(permissionPo.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", permissionPo.getUrl());
			attributes.put("icon", permissionPo.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<PermissionPo> per = BuildTree.build(trees);
		return per;
	}

	@Override
	public List<Tree<PermissionPo>> listPermissionTree(Long id) {
		List<Tree<PermissionPo>> trees = new ArrayList<Tree<PermissionPo>>();
		List<PermissionPo> permissionPos = permissionDao.findListByUserId(id);
		for (PermissionPo permissionPo : permissionPos) {
			Tree<PermissionPo> tree = new Tree<PermissionPo>();
			tree.setId(permissionPo.getId().toString());
			tree.setParentId(permissionPo.getParent_id().toString());
			tree.setText(permissionPo.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", permissionPo.getUrl());
			attributes.put("icon", permissionPo.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<PermissionPo>> list = BuildTree.buildList(trees, "0");
		return list;
	}

	@Override
	public Tree<PermissionPo> getTree() {
		return null;
	}

	@Override
	public Tree<PermissionPo> getTree(Long id) {
		return null;
	}

	@Override
	public List<PermissionPo> list() {
		List<PermissionPo> permissionPos = permissionDao.findAll();
		return permissionPos;
	}

	@Override
	public int remove(Long id) {
		return 0;
	}

	@Override
	public int save(PermissionPo menu) {
		return 0;
	}

	@Override
	public int update(PermissionPo menu) {
		return 0;
	}

	@Override
	public PermissionPo get(Long id) {
		return null;
	}

	@Override
	public Set<String> listPerms(Long userId) {
		Set<String> permissionPos = permissionDao.findSetByUserId(userId);
		return permissionPos;
	}
}
