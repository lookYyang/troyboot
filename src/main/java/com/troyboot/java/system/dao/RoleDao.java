package com.troyboot.java.system.dao;

import com.troyboot.java.system.po.PermissionPo;
import com.troyboot.java.system.po.RolePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Set;


/**
 *
 * 使用jpa和querydsl结合做查询
 */
public interface RoleDao extends JpaRepository<RolePo, Long>{

    @Query(value = "select role.role from sys_role role " +
            "left join sys_user_role ur on ur.role_id = role.id " +
            "where ur.user_id = ?1",
            nativeQuery = true)
    public Set<String> findRolesByUserId(Long id);

}
