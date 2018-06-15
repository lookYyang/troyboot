package com.troyboot.java.system.dao;

import com.troyboot.java.system.po.PermissionPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import reactor.util.annotation.NonNullApi;

import java.util.List;
import java.util.Set;


/**
 *
 * 使用jpa和querydsl结合做查询
 */
public interface PermissionDao extends JpaRepository<PermissionPo, Long>{

    @Query(value = "select per.permission from sys_permission per " +
            "left join sys_role_permission rp on rp.permission_id = per.id " +
            "left join sys_user_role ur on ur.role_id = rp.role_id " +
            "where ur.user_id = ?1 and per.permission is not NULL and per.permission != ''",
            nativeQuery = true)
    Set<String> findSetByUserId(long id);

    @Query(value = "select per.* from sys_permission per " +
            "left join sys_role_permission rp on rp.permission_id = per.id " +
            "left join sys_user_role ur on ur.role_id = rp.role_id where ur.user_id = ?1 and per.type in (0,1) ORDER BY per.sort_no",
            nativeQuery = true)
    List<PermissionPo> findListByUserId(long id);


    @Query(value = "select * from sys_permission", nativeQuery = true)
    List<PermissionPo> findAll();

//     save(PermissionPo permissionPo);
//
//    int update(PermissionPo permissionPo);
//
//    int remove(Long menuId);
}
