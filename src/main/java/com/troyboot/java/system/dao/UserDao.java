package com.troyboot.java.system.dao;

import com.troyboot.java.system.po.UserPo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 *
 * 使用jpa和querydsl结合做查询
 */
@CacheConfig(cacheNames = "users")
public interface UserDao extends JpaRepository<UserPo, Long>{

    @Cacheable
    UserPo findById(long id);

    List<UserPo> findByName(String name);

    UserPo findByAccount(String account);



}
