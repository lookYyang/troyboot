package com.troyboot.java.system.controller;

import com.troyboot.java.common.utils.OutMessage;
import com.troyboot.java.system.po.PermissionPo;
import com.troyboot.java.system.po.UserPo;
import com.troyboot.java.system.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Authour YangYang
 * @Date 2018/6/7 16:07
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    private String prefix = "system/permission";

    @Autowired
    private PermissionService permissionService;


    @RequiresPermissions("sys:menu:menu")
    @RequestMapping("/list")
    @ResponseBody
    List<PermissionPo> list(@RequestParam Map<String, Object> params) {
        List<PermissionPo> permissionPos = permissionService.list(params);
        return permissionPos;
    }

}
