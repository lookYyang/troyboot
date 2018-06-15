package com.troyboot.java.system.controller;

import com.troyboot.java.system.po.SysPermission;
import com.troyboot.java.system.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    String perContent() {
        return prefix + "/permission";
    }

    @ApiOperation(value = "获取所有权限信息", httpMethod = "GET",response = SysPermission.class, notes = "获取所有权限信息")
    @ApiResponses({ @ApiResponse(code = 400, message = "Invalid Order") })
    @RequiresPermissions("sys:permission:permission")
    @GetMapping("/list")
    @ResponseBody
    List<SysPermission> list() {
        List<SysPermission> permissionPos = permissionService.list();
        return permissionPos;
    }




}
