package com.troyboot.java.system.service.dao;

import com.troyboot.java.system.po.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Authour YangYang
 * @Date 2018/6/15 12:17
 */
@Repository
@Mapper
public interface SysRoleDao {

    @Select("select role.role from sys_role role" +
            " left join sys_user_role ur on ur.role_id = role.id" +
            " where ur.user_id = #{id}")
    List<String> getSysRolesByUserId(Long id);
}
