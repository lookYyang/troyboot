package com.troyboot.java.system.controller;

import com.troyboot.java.common.utils.MD5Utils;
import com.troyboot.java.common.utils.ShiroUtils;
import com.troyboot.java.system.po.UserPo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Authour YangYang
 * @Date 2018/6/7 15:59
 */

@Controller
@RequestMapping
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping(value = "/login")
    public String submitLogin(String username, String password, RedirectAttributes redirectAttributes) {
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = ShiroUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            subject.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        }catch(UnknownAccountException uae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addFlashAttribute("message", "未知账户");
        }catch(LockedAccountException lae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            redirectAttributes.addFlashAttribute("message", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if(ShiroUtils.getSubject().isAuthenticated()){
            logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
            System.out.println("是否拥有管理员权限："+ subject.hasRole("admin"));
            System.out.println("是否拥有vip会员权限："+ subject.hasRole("vip"));
            
            return "redirect:/index";
        }else{
            token.clear();
            return "redirect:/login";
        }
    }


    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/index")
    public String loginSuccessMessage(HttpServletRequest request) {
        String username = "未登录";
        UserPo userPo = ShiroUtils.getUser();

        if (userPo != null && StringUtils.isNotEmpty(userPo.getName())) {
            username = userPo.getName();
        } else {
            return "redirect:/login";
        }
        request.setAttribute("username", username);
        return "index";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        ShiroUtils.logout();
        return "index";
    }

    //被踢出后跳转的页面
    @GetMapping(value = "/kickout")
    public String kickOut() {
        return "kickout";
    }

}
