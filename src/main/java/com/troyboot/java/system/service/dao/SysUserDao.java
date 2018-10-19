package com.troyboot.java.system.service.dao;

import com.troyboot.java.system.po.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Authour YangYang
 * @Date 2018/6/15 11:44
 */
@Repository
@Mapper
public interface SysUserDao {
    SysUser getUserByAccount(String account);

    List<SysUser> selectAll();
}
