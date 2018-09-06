package com.troyboot.java.system.service.dao;

import com.troyboot.java.system.po.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Authour YangYang
 * @Date 2018/6/15 11:58
 */
@Repository
@Mapper
public interface SysPermissionDao {

    List<SysPermission> selectAll();

    @Select("select per.permission" +
            " from sys_permission per" +
            " left join sys_role_permission rp on per.id = rp.permission_id" +
            " left join sys_user_role ur on ur.role_id = rp.role_id" +
            " where ur.user_id = #{id}" +
            " and per.permission is not null")
    List<String> getPersByUserId(Long id);

    @Select("select per.*" +
            " from sys_permission per" +
            " left join sys_role_permission rp on per.id = rp.permission_id" +
            " left join sys_user_role ur on ur.role_id = rp.role_id" +
            " where ur.user_id = #{id}" +
            "       and per.type in (0,1)" +
            " order by id desc")
    List<SysPermission> getPermissionByUserId(Long id);

    @Select("select * from sys_permission per where per.permission is not null and per.url is not null order by id asc")
    List<SysPermission> list();
}
