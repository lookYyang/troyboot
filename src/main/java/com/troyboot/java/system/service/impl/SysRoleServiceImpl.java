package com.troyboot.java.system.service.impl;

import com.troyboot.java.system.service.SysRoleService;
import com.troyboot.java.system.service.dao.SysRoleDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Authour YangYang
 * @Date 2018/6/15 12:06
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    public Set<String> getSysRoleByUserId(Long userId){
        List<String> setRoles = sysRoleDao.getSysRolesByUserId(userId);
        Set<String> rolesSet = new HashSet<>();
        for (String perm : setRoles) {
            if (StringUtils.isNotBlank(perm)) {
                rolesSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return rolesSet;
    }

}
