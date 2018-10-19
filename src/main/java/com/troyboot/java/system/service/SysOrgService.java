package com.troyboot.java.system.service;

import com.troyboot.java.common.utils.OutMessage;
import com.troyboot.java.common.utils.PageUtils;
import com.troyboot.java.system.po.SysOrg;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Authour YangYang
 * @Date 2018/6/15 11:39
 */
@Service
public interface SysOrgService {

    PageUtils list(Map<String, Object> params);

    OutMessage getTree();

    OutMessage save(SysOrg sysOrg);

    OutMessage remove(int id);

    OutMessage batchRemove(int[] ids);
}
