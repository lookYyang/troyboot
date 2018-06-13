package com.troyboot.java.system.security.shiro;

import com.troyboot.java.common.utils.MD5Utils;
import com.troyboot.java.common.utils.ShiroUtils;
import com.troyboot.java.system.common.Constant;
import com.troyboot.java.system.config.ApplicationContextRegister;
import com.troyboot.java.system.po.UserPo;
import com.troyboot.java.system.service.PermissionService;
import com.troyboot.java.system.service.RoleService;
import com.troyboot.java.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;

/**
 * @Authour YangYang
 * @Date 2018/6/7 10:39
 */
public class ShiroRealm extends AuthorizingRealm {

    /**
     *
     * 获取用户权限，角色信息进行配置
     *
     * **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限分配");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        PermissionService permissionService = ApplicationContextRegister.getBean(PermissionService.class);
        RoleService roleService = ApplicationContextRegister.getBean(RoleService.class);
        Long user_id = ShiroUtils.getUser().getId();
        authorizationInfo.addStringPermissions(permissionService.findByUserId(user_id));
        authorizationInfo.setRoles(roleService.findRolesByUserId(user_id));
        return authorizationInfo;
    }

    /**
     *
     * 进行常规的信息验证
     *
     * **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("用户认证");
        String account = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        UserService userService = ApplicationContextRegister.getBean(UserService.class);
        // 查询用户信息
        UserPo user = userService.getUserByAccount(account);

        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }else if(!password.equals(user.getPassword())){
            throw new UnknownAccountException("账号或密码不正确");
        }else {
            // 账号锁定
            if (user.getStatus() == 0) {
                throw new LockedAccountException("账号已被锁定,请联系管理员");
            }else {
                // 第一个参数必须是一个实体类对象
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, password, getName());
                //设置盐，用来核对密码
                authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(Constant.PWD_SALT));
                return authenticationInfo;
            }
        }
    }
}
