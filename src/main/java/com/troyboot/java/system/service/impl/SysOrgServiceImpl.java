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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        List<SysOrg> sysOrgs = sysOrgDao.selectAll();
        PageInfo<SysOrg> pageInfo = new PageInfo<>(sysOrgs);
        PageUtils pageUtils = new PageUtils(sysOrgs, pageInfo.getTotal());
        return pageUtils;
    }

    @Override
    public Tree<SysOrg> getTree() {
        List<Tree<SysOrg>> trees = new ArrayList<>();
        int org_id = ShiroUtils.getUser().getOrgId();
        SysOrg org = sysOrgMapper.selectByPrimaryKey(org_id);
        List<SysOrg> sysOrgs = sysOrgDao.getTreeByCascade(org.getCascadeId());
        for (SysOrg sysOrg : sysOrgs) {
            Tree<SysOrg> tree = new Tree<>();
            tree.setId(sysOrg.getId().toString());
            tree.setParentId(sysOrg.getParentId().toString());
            tree.setTitle(sysOrg.getName());
            tree.setExpand(sysOrg.getIsExpanded().equals(1) ? true: false);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysOrg> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public OutMessage save(SysOrg sysOrg) {
        OutMessage om = new OutMessage();
        // 生成语义ID
        SysOrg parentOrg = sysOrgMapper.selectByPrimaryKey(sysOrg.getParentId());
        String max_cascade_id = sysOrgDao.getMaxCascadeId(sysOrg.getParentId());
        if (CommonUtils.isEmpty(max_cascade_id)) {
            String temp = "0";
            if (CommonUtils.isEmpty(parentOrg)) {
                temp = parentOrg.getCascadeId();
            }
            max_cascade_id = temp + ".000";
        }
        String cascade_id = CommonUtils.genCascadeTreeId(max_cascade_id, 999);
        sysOrg.setCascadeId(cascade_id);
        sysOrg.setCreateBy(ShiroUtils.getUser().getId().toString());
        sysOrg.setCreateTime(new Date());
        if(sysOrgMapper.insert(sysOrg) > 0){
            return om.ok("插入成功");
        }
        return om.error("数据操作失败，请检查数据格式是否正确，或联系管理员！");
    }

    @Override
    public OutMessage remove(int id) {
        OutMessage om = new OutMessage();
        if(sysOrgMapper.deleteByPrimaryKey(id) > 0){
            return om.ok("删除成功");
        }
        return om.error("数据操作失败，请检查数据格式是否正确，或联系管理员！");
    }

    @Override
    public OutMessage batchRemove(int[] ids) {
        OutMessage om = new OutMessage();
        if(sysOrgDao.batchRemove(ids) > 0){
            return om.ok("删除成功");
        }
        return om.error("数据操作失败，请检查数据格式是否正确，或联系管理员！");
    }
}
