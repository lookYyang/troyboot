package com.troyboot.java.common.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.troyboot.java.common.utils.PageUtils;
import com.troyboot.java.common.dao.SysLogMapper;
import com.troyboot.java.common.po.SysLog;
import com.troyboot.java.system.po.SysUser;
import com.troyboot.java.common.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SysLogServiceImpl implements SysLogService {
	@Autowired
    SysLogMapper logMapper;

	@Async
	@Override
	public void save(SysLog logDO) {
		 logMapper.insert(logDO);
	}

	@Override
	public PageUtils queryList(Map<String, Object> params) {
        int limit = params.get("limit") == "" ? 1 : Integer.valueOf(params.get("limit").toString());
        int offset = params.get("offset") == "" ? 15 : Integer.valueOf(params.get("offset").toString());
        PageHelper.startPage(limit, offset, true);
        List<SysUser> sysUsers = new ArrayList<>();
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        PageUtils pageUtils = new PageUtils(sysUsers, pageInfo.getTotal());
        return pageUtils;
	}

	@Override
	public void remove(int id) {
		logMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchRemove(int[] ids){
	    for (int id : ids){
	        logMapper.deleteByPrimaryKey(id);
        }
	}
}
