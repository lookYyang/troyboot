package com.troyboot.java.system.service;

import com.gitee.hengboy.mybatis.pageable.Page;
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

    Page<SysUser> selectAllPage(Map<String,Object> params);
}
