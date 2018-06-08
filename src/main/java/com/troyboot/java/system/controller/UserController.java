package com.troyboot.java.system.controller;

import com.troyboot.java.system.po.UserPo;
import com.troyboot.java.system.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Authour YangYang
 * @Date 2018/6/7 16:07
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询批量用户信息", httpMethod = "GET", response = UserPo.class, notes = "用户批量查询")
    public String listUsers(Model model){
        List<UserPo> userPos = userService.getAllUsers();
        model.addAttribute("userPos", userPos);
        return "userInfo";
    }


    @GetMapping("/user/{id}")
    @ApiOperation(value = "通过id查询单个用户信息", httpMethod = "GET", response = UserPo.class, notes = "通过id查询用户")
    public String detail(@PathVariable int id, Model model){
        UserPo sysUser = userService.getUserById(id);
        model.addAttribute("sysUser", sysUser);
        return "users";
    }
}
