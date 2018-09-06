package com.troyboot.java.system.service.dao;

import com.troyboot.java.system.po.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Authour YangYang
 * @Date 2018/6/15 11:44
 */
@Repository
@Mapper
public interface SysUserDao {

    @Select("select * from sys_user where account = #{account}")
    SysUser getUserByAccount(String account);

    List<SysUser> selectAll();
}
