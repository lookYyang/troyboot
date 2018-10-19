package com.troyboot.java.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.troyboot.java.common.utils.*;
import com.troyboot.java.system.dao.SysOrgMapper;
import com.troyboot.java.system.domain.Tree;
import com.troyboot.java.system.po.SysOrg;
import com.troyboot.java.system.service.SysOrgService;
import com.troyboot.java.system.service.dao.SysOrgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysOrgServiceImpl implements SysOrgService {

    @Autowired
    private SysOrgDao sysOrgDao;

    @Autowired
    private SysOrgMapper sysOrgMapper;

    @Override
    public PageUtils list(Map<String, Object> params) {
        int limit = params.get("limit") == "" ? 1 : Integer.valueOf(params.get("limit").toString());
        int offset = params.get("offset") == "" ? 15 : Integer.valueOf(params.get("offset").toString());
        PageHelper.startPage(limit, offset, true);
        List<Map<String, Object>> sysOrgs = sysOrgDao.selectAll();
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(sysOrgs);
        PageUtils pageUtils = new PageUtils(sysOrgs, pageInfo.getTotal());
        return pageUtils;
    }

    @Override
    public OutMessage getTree() {
        OutMessage om = new OutMessage();
        List<Tree<SysOrg>> trees = new ArrayList<>();
        int org_id = ShiroUtils.getUser().getOrg_id();
        SysOrg org = sysOrgMapper.selectByPrimaryKey(org_id);
        String cascadeId = org.getCascade_id();
        Map<String, Object> params = new HashMap<>();
        params.put("cascadeId", cascadeId);
        List<SysOrg> sysOrgs = sysOrgDao.getTreeByCascade(params);
        for (SysOrg sysOrg : sysOrgs) {
            Tree<SysOrg> tree = new Tree<>();
            tree.setId(sysOrg.getId().toString());
            tree.setParentId(sysOrg.getParent_id().toString());
            tree.setTitle(sysOrg.getName());
            tree.setExpand(sysOrg.getIs_expanded().equals(1) ? true: false);
            tree.setCascadeId(sysOrg.getCascade_id());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<SysOrg>> t = BuildTree.buildList(trees, "0");
        return om.put("data", t);
    }

    @Override
    public OutMessage save(SysOrg sysOrg) {
        // 生成语义ID
        SysOrg parentOrg = sysOrgMapper.selectByPrimaryKey(sysOrg.getParent_id());
        String max_cascade_id = sysOrgDao.getMaxCascadeId(sysOrg.getParent_id());
        if (CommonUtils.isEmpty(max_cascade_id)) {
            String temp = "0";
            if (CommonUtils.isEmpty(parentOrg)) {
                temp = parentOrg.getCascade_id();
            }
            max_cascade_id = temp + ".000";
        }
        String cascade_id = CommonUtils.genCascadeTreeId(max_cascade_id, 999);
        sysOrg.setCascade_id(cascade_id);
        sysOrg.setCreate_by(ShiroUtils.getUser().getId().toString());
        sysOrg.setCreate_time(new Date());
        if(sysOrgMapper.insert(sysOrg) > 0){
            return OutMessage.ok("插入成功");
        }
        return OutMessage.error("数据操作失败，请检查数据格式是否正确，或联系管理员！");
    }

    @Override
    public OutMessage remove(int id) {
        if(sysOrgMapper.deleteByPrimaryKey(id) > 0){
            return OutMessage.ok("删除成功");
        }
        return OutMessage.error("数据操作失败，请检查数据格式是否正确，或联系管理员！");
    }

    @Override
    public OutMessage batchRemove(int[] ids) {
        if(sysOrgDao.batchRemove(ids) > 0){
            return OutMessage.ok("删除成功");
        }
        return OutMessage.error("数据操作失败，请检查数据格式是否正确，或联系管理员！");
    }
}
