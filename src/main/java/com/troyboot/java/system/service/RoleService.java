package com.troyboot.java.system.service;

import com.troyboot.java.system.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Authour YangYang
 * @Date 2018/6/7 10:43
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Set<String> findRolesByUserId(Long userid){
        return roleDao.findRolesByUserId(userid);
    }

}
