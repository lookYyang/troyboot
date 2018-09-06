package com.troyboot.java.system.controller;

import com.troyboot.java.common.utils.OutMessage;
import com.troyboot.java.common.utils.PageUtils;
import com.troyboot.java.common.utils.ShiroUtils;
import com.troyboot.java.system.po.SysUser;
import com.troyboot.java.system.service.SysUserService;
import io.swagger.annotations.*;
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
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    private String prefix = "/mapper/system/user";

    @RequiresPermissions("sys:user:user")
    @GetMapping("")
    String user(Model model) {
        return prefix + "/userInfo";
    }

    @GetMapping("/personal")
    @ApiOperation(value = "获取当前用户信息", httpMethod = "GET", response = SysUser.class, notes = "获取当前登录用户信息")
    public String personal(Model model){
        SysUser sysUser = ShiroUtils.getUser();
        model.addAttribute("personal", sysUser);
        // TODO
        // 这个地方先这样显示一下，到时候修改用户的时候，这里返回到修改用户的界面
        return "main";
    }

    @ApiOperation(value = "查询用户", notes = "分页查询用户所有")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "当前页码",
                    dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页显示条数",
                    dataType = "Integer", paramType = "query")
    })
    @ApiResponses({ @ApiResponse(code = 400, message = "Invalid Order") })
    @PostMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        return sysUserService.selectAllPage(params);
    }

    @PostMapping("/add")
    public OutMessage addUser(SysUser sysUser){
        sysUserService.addUser(sysUser);
        return OutMessage.ok();
    }
}
