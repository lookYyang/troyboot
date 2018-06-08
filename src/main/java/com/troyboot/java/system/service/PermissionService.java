package com.troyboot.java.system.service;

import com.troyboot.java.system.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Authour YangYang
 * @Date 2018/6/7 10:43
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    public Set<String> findByUserId(long userId){
        return permissionDao.findByUserId(userId);
    }

}
