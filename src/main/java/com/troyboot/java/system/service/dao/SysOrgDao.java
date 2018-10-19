package com.troyboot.java.system.service.dao;

import com.troyboot.java.system.po.SysOrg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Authour YangYang
 * @Date 2018/6/15 11:44
 */
@Repository
@Mapper
public interface SysOrgDao {

    List<Map<String, Object>> selectAll();

    List<SysOrg> getTreeByCascade(Map<String, Object> params);

    int batchRemove(int[] ids);

    String getMaxCascadeId(int parent_id);
}
