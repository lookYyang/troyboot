package com.troyboot.java.system.service;

import com.troyboot.java.common.utils.PageUtils;
import com.troyboot.java.system.po.SysUser;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Authour YangYang
 * @Date 2018/6/15 11:39
 */
@Service
public interface SysUserService {

    SysUser getUserById(Long id);

    void addUser(SysUser sysUser);

    PageUtils selectAllPage(Map<String, Object> params);
}
