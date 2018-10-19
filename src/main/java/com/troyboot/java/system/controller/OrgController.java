package com.troyboot.java.system.controller;

import com.troyboot.java.common.annotation.Log;
import com.troyboot.java.common.utils.OutMessage;
import com.troyboot.java.common.utils.PageUtils;
import com.troyboot.java.system.po.SysOrg;
import com.troyboot.java.system.service.SysOrgService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Authour YangYang
 * @Date 2018/6/7 16:07
 */
@Controller
@RequestMapping("/sys/org")
@Api("组织机构管理")
public class OrgController {

    @Autowired
    private SysOrgService sysOrgService;

    @GetMapping("")
    String org() {
        return "/system/org";
    }

    @ApiOperation(value = "查询组织机构", notes = "分页查询用户当前机构下的机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "当前页码",
                    dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "offset", value = "每页显示条数",
                    dataType = "Integer", paramType = "query")
    })
    @ApiResponses({ @ApiResponse(code = 400, message = "Invalid Order") })
    @PostMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        return sysOrgService.list(params);
    }

    @ApiOperation(value = "获取树的结构")
    @ApiResponses({ @ApiResponse(code = 400, message = "Invalid Order") })
    @GetMapping("/orgTree")
    @ResponseBody
    public OutMessage getTree(){
        return sysOrgService.getTree();
    }

    @PostMapping("/save")
    @Log("添加组织机构")
    public OutMessage add(SysOrg sysOrg){
        return sysOrgService.save(sysOrg);
    }

    @PostMapping("/remove")
    @Log("删除组织机构")
    public OutMessage remove(@RequestParam int id){
        return sysOrgService.remove(id);
    }

    @PostMapping("/batchRemove")
    @Log("批量删除组织机构")
    public OutMessage batchRemove(int[] ids){
        return sysOrgService.batchRemove(ids);
    }
}
