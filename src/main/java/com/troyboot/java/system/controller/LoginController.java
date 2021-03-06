package com.troyboot.java.system.controller;

import com.troyboot.java.common.utils.OutMessage;
import com.troyboot.java.common.utils.ShiroUtils;
import com.troyboot.java.system.domain.Tree;
import com.troyboot.java.system.po.SysPermission;
import com.troyboot.java.system.po.SysUser;
import com.troyboot.java.system.service.SysPermissionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Authour YangYang
 * @Date 2018/6/7 15:59
 */

@Controller
@Api("用户登录，登出")
@Slf4j
public class LoginController {

    @Autowired
    private SysPermissionService permissionService;

    @PostMapping(value = "/login")
    @ResponseBody
    OutMessage login(String account, String password) {
//        password = MD5Utils.encrypt(account, password);
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        Subject subject = ShiroUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到ShiroRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            log.info("对用户[" + account + "]进行登录验证..验证开始");
            subject.login(token);
            log.info("对用户[" + account + "]进行登录验证..验证通过");
            return OutMessage.ok("登录成功");
        }catch (LockedAccountException le){
            log.info(le.getMessage());
            return OutMessage.error("账号已被锁定,请联系管理员");
        }
        catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            log.info("对用户[" + account + "]进行登录验证..验证未通过,堆栈轨迹如下");
            return OutMessage.error("用户名或密码不正确");
        }
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/index")
    public String loginSuccessMessage(Model model) {
        SysUser userPo = ShiroUtils.getUser();
        if (userPo != null && StringUtils.isNotEmpty(userPo.getName())) {
            List<Tree<SysPermission>> perTree = permissionService.getPermissionByUserId(userPo.getId());
            model.addAttribute("menus", perTree);
            model.addAttribute("name", userPo.getName());
            return "index";
        } else {
            return "login";
        }
    }

    @GetMapping(value = "/logout")
    public String logout() {
        ShiroUtils.logout();
        return "index";
    }

    //被踢出后跳转的页面
    @GetMapping(value = "/kickout")
    public String kickOut() {
        ShiroUtils.logout();
        return "index";
    }

    // 主界面
    @GetMapping("/main")
    String main() {
        return "main";
    }

    // REST API接口界面
    @GetMapping(value = "/swagger")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
