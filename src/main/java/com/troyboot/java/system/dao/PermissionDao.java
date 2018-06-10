package com.troyboot.java.system.dao;

import com.troyboot.java.system.po.PermissionPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Set;


/**
 *
 * 使用jpa和querydsl结合做查询
 */
public interface PermissionDao extends JpaRepository<PermissionPo, Long>{

    @Query(value = "select per.* from sys_permission per " +
            "left join sys_role_permission rp on rp.permission_id = per.id " +
            "left join sys_user_role ur on ur.role_id = rp.role_id where ur.user_id = ?1",
            nativeQuery = true)
    Set<String> findByUserId(long id);

}
