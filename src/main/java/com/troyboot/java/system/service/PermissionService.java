package com.troyboot.java.system.service;

import com.troyboot.java.system.domain.Tree;
import com.troyboot.java.system.po.PermissionPo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface PermissionService {
	Tree<PermissionPo> getPermissionTree(Long id);

	List<Tree<PermissionPo>> listPermissionTree(Long id);

	Tree<PermissionPo> getTree();

	Tree<PermissionPo> getTree(Long id);

	List<PermissionPo> list(Map<String, Object> params);

	int remove(Long id);

	int save(PermissionPo menu);

	int update(PermissionPo menu);

	PermissionPo get(Long id);

	Set<String> listPerms(Long userId);
}
