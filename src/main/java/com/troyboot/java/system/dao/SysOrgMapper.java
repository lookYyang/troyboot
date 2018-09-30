package com.troyboot.java.system.dao;

import com.troyboot.java.system.po.SysOrg;

public interface SysOrgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysOrg record);

    int insertSelective(SysOrg record);

    SysOrg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysOrg record);

    int updateByPrimaryKey(SysOrg record);
}