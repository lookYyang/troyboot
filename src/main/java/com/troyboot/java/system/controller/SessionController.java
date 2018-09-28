package com.troyboot.java.system.controller;

import com.troyboot.java.common.utils.OutMessage;
import com.troyboot.java.common.utils.PageUtils;
import com.troyboot.java.system.domain.UserOnline;
import com.troyboot.java.system.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

@Controller
@RequestMapping("/sys/online")
@Api("在线用户管理")
public class SessionController {

    private String prefix = "/system/user";

    @Autowired
    private SessionService sessionService;

    @GetMapping("")
    public String online() {
        return prefix + "/online";
    }

    @RequiresPermissions("sys:online:list")
    @ApiOperation(value = "获取在线用户", httpMethod = "GET", response= UserOnline.class,notes = "获取在线用户信息")
    @ApiResponses({ @ApiResponse(code = 400, message = "Invalid Order") })
    @GetMapping("/list")
    @ResponseBody
    public PageUtils list() {
        List<UserOnline> userOnlines = sessionService.list();
        PageUtils sessions = new PageUtils(userOnlines, Long.valueOf(userOnlines.size()));
        return sessions;
    }

    @RequiresPermissions("sys:online:forceLogout")
    @ApiOperation(value = "强迫用户下线", httpMethod = "POST", notes = "强迫用户下线")
    @ApiResponses({ @ApiResponse(code = 400, message = "Invalid Order") })
    @PostMapping("/forceLogout")
    @ResponseBody
    public OutMessage forceLogout(String id) {
        try {
            sessionService.forceLogout(id);
            return OutMessage.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return OutMessage.error("踢出用户失败");
        }
    }
}
