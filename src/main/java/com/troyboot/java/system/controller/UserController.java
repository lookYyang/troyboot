package com.troyboot.java.system.controller;

import com.troyboot.java.common.utils.OutMessage;
import com.troyboot.java.system.po.UserPo;
import com.troyboot.java.system.service.UserService;
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

    @Autowired
    private UserService userService;

    @RequiresPermissions("sys:user:user")
    @GetMapping("")
    String user(Model model) {
        return prefix + "/userInfo";
    }

    @GetMapping("list")
    @ApiOperation(value = "查询批量用户信息", httpMethod = "GET", response = UserPo.class, notes = "用户批量查询")
    public String listUsers(Model model){
        List<UserPo> userPos = userService.getAllUsers();
        model.addAttribute("userPos", userPos);
        return prefix + "/userInfo";
    }


    @GetMapping("/user/{id}")
    @ApiOperation(value = "通过id查询单个用户信息", httpMethod = "GET", response = UserPo.class, notes = "通过id查询用户")
    public String detail(@PathVariable int id, Model model){
        UserPo sysUser = userService.getUserById(id);
        model.addAttribute("userPos", sysUser);
        return prefix + "/userInfo";
    }

    @RequiresPermissions("sys:user:remove")
    @GetMapping("/remove/{id}")
    @ApiOperation(value = "通过删除单个用户信息", httpMethod = "GET", response = UserPo.class, notes = "通过id删除用户")
    OutMessage remove(@PathVariable Long id){
        if(userService.remove(id) > 0){
            return OutMessage.ok();
        }else {
            return OutMessage.error();
        }
    }
}
