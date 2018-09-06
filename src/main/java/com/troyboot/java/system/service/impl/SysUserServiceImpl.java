package com.troyboot.java.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.troyboot.java.common.utils.CommonUtils;
import com.troyboot.java.common.utils.PageUtils;
import com.troyboot.java.system.dao.SysUserMapper;
import com.troyboot.java.system.po.SysUser;
import com.troyboot.java.system.service.SysUserService;
import com.troyboot.java.system.service.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public SysUser getUserById(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public void addUser(SysUser sysUser) {
        sysUser.setName("test");
        sysUser.setAccount("123123");
        sysUser.setIsDelete(1);
        sysUser.setPassword("1123123");
        sysUser.setStatus(1);
        sysUserMapper.insert(sysUser);
	}

    @Override
	public PageUtils selectAllPage(Map<String, Object> params) {
	    int limit = params.get("limit") == "" ? 1 : Integer.valueOf(params.get("limit").toString());
        int size = params.get("size") == "" ? 10 : Integer.valueOf(params.get("size").toString());
        PageHelper.startPage(limit, size, true);
        List<SysUser> sysUsers = sysUserDao.selectAll();
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        PageUtils pageUtils = new PageUtils(sysUsers, pageInfo.getTotal());
		return pageUtils;
	}

    public SysUser getUserByAccount(String account){
		return sysUserDao.getUserByAccount(account);
	}

}
