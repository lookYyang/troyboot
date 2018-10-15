package com.troyboot.java.system.service;

import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Authour YangYang
 * @Date 2018/6/15 12:06
 */
@Service
public interface SysRoleService {

    public Set<String> getSysRoleByUserId(int id);

}
