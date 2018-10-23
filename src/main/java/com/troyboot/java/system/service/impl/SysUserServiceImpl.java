package com.troyboot.java.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.troyboot.java.common.utils.OutMessage;
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
	public SysUser getUserById(int id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public OutMessage addUser(SysUser sysUser) {
		OutMessage om = new OutMessage();
		if( sysUserMapper.insert(sysUser) > 0){
			return om.ok("插入成功");
		}
		return om.error("数据插入失败，请检查数据格式或联系管理员！");
	}

    @Override
	public PageUtils list(Map<String, Object> params) {
        PageHelper.startPage(params);
        List<SysUser> sysUsers = sysUserDao.selectAll();
        for(SysUser user : sysUsers){
			user.setPassword("");
		}
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        PageUtils pageUtils = new PageUtils(sysUsers, pageInfo.getTotal());
		return pageUtils;
	}

    public SysUser getUserByAccount(String account){
		return sysUserDao.getUserByAccount(account);
	}

}
