package com.troyboot.java.system.service.impl;

import com.gitee.hengboy.mybatis.pageable.Page;
import com.gitee.hengboy.mybatis.pageable.request.PageableRequest;
import com.troyboot.java.system.dao.SysUserMapper;
import com.troyboot.java.system.po.SysUser;
import com.troyboot.java.system.service.SysUserService;
import com.troyboot.java.system.service.dao.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		sysUser.setId(new Long(12));
		sysUser.setName("test");
		sysUser.setAccount("123123");
		sysUser.setIsDelete(1);
		sysUser.setPassword("1123123");
		sysUser.setStatus(1);
		sysUserMapper.insert(sysUser);
	}

	public Page<SysUser> selectAllPage(Map<String, Object> params) {
		Page<SysUser> page = PageableRequest.of(1, 5).request(() -> sysUserDao.selectAll());
		return page;
	}

    public SysUser getUserByAccount(String account){
		return sysUserDao.getUserByAccount(account);
	}

}
