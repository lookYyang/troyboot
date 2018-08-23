package com.troyboot.java.system.controller;

import com.gitee.hengboy.mybatis.pageable.Page;
import com.troyboot.java.common.utils.OutMessage;
import com.troyboot.java.common.utils.ShiroUtils;
import com.troyboot.java.system.po.SysUser;
import com.troyboot.java.system.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "获取用户信息", httpMethod = "POST",response = SysUser.class, notes = "获取用户信息")
    @ApiResponses({ @ApiResponse(code = 400, message = "Invalid Order") })
    @GetMapping("/list")
    @ResponseBody
    Page<SysUser> list(@RequestParam Map<String, Object> params) {
        Page<SysUser> sysUsers = sysUserService.selectAllPage(params);
        return sysUsers;
    }

    @PostMapping("/add")
    public OutMessage addUser(SysUser sysUser){
        sysUserService.addUser(sysUser);
        return OutMessage.ok();
    }
}
