package com.troyboot.java.system.controller;

import com.troyboot.java.common.utils.OutMessage;
import com.troyboot.java.common.utils.ShiroUtils;
import com.troyboot.java.system.po.SysUser;
import com.troyboot.java.system.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Authour YangYang
 * @Date 2018/6/7 16:07
 */
@Controller
@RequestMapping("/userInfo")
public class UserController {

    private String prefix = "system/user";

    @RequiresPermissions("sys:user:user")
    @GetMapping("")
    String user(Model model) {
        return prefix + "/userInfo";
    }

    @GetMapping("/personal")
    @ApiOperation(value = "获取当前用户信息", httpMethod = "GET", response = SysUser.class, notes = "获取当前登录用户信息")
    public String personal(Model model){
        SysUser userPo = ShiroUtils.getUser();
        model.addAttribute("personal", userPo);
        // TODO
        // 这个地方先这样显示一下，到时候修改用户的时候，这里返回到修改用户的界面
        return "main";
    }
}
