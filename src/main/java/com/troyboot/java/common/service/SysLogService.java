package com.troyboot.java.common.service;

import com.troyboot.java.common.utils.PageUtils;
import com.troyboot.java.common.po.SysLog;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface SysLogService {
	void save(SysLog logDO);
	PageUtils queryList(Map<String, Object> params);
	void remove(int id);
	void batchRemove(int[] ids);
}
