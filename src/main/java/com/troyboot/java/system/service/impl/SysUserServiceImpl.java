package com.troyboot.java.system.service.impl;

import com.troyboot.java.system.dao.SysUserMapper;
import com.troyboot.java.system.po.SysUser;
import com.troyboot.java.system.service.SysUserService;
import com.troyboot.java.system.service.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public SysUser getUserById(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	public SysUser getUserByAccount(String account){
		return sysUserDao.getUserByAccount(account);
	}

}
