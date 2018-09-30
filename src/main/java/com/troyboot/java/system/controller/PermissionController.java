package com.troyboot.java.system.controller;

import com.troyboot.java.common.utils.OutMessage;
import com.troyboot.java.common.utils.PageUtils;
import com.troyboot.java.system.po.SysPermission;
import com.troyboot.java.system.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Authour YangYang
 * @Date 2018/6/7 16:07
 */
@Controller
@RequestMapping("/sys/permission")
@Api("权限管理")
public class PermissionController {

    private String prefix = "/system/permission";

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @GetMapping("")
    String perContent() {
        return prefix + "/permission";
    }

    @ApiOperation(value = "获取所有权限信息", httpMethod = "GET",response = SysPermission.class, notes = "获取所有权限信息")
    @ApiResponses({ @ApiResponse(code = 400, message = "Invalid Order") })
    @PostMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        PageUtils sysPermissions = sysPermissionService.list(params);
        return sysPermissions;
    }

    @RequiresPermissions("sys:permission:add")
    @GetMapping("/add/{id}")
    String add(@PathVariable("id") Long id, Model model){
        SysPermission sysPermission = permissionService.getPermissionById(id);
        if(StringUtils.isEmpty(sysPermission.getId().toString())){
            model.addAttribute("per", "");
        }else {
            model.addAttribute("per", sysPermission);
        }
        return prefix + "/add";
    }

    @ApiOperation(value = "保存权限信息", httpMethod = "POST",response = SysPermission.class, notes = "保存权限")
    @RequiresPermissions("sys:menu:add")
    @GetMapping("/save")
    @ResponseBody
    OutMessage save(SysPermission sysPermission){
        return OutMessage.ok();
    }

}
