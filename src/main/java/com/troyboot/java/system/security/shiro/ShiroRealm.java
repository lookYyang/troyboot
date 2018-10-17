package com.troyboot.java.system.security.shiro;

import com.troyboot.java.common.utils.ShiroUtils;
import com.troyboot.java.common.Constant;
import com.troyboot.java.system.config.ApplicationContextRegister;
import com.troyboot.java.system.po.SysUser;
import com.troyboot.java.system.service.SysPermissionService;
import com.troyboot.java.system.service.SysRoleService;
import com.troyboot.java.system.service.impl.SysUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @Authour YangYang
 * @Date 2018/6/7 10:39
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    {
        SimpleCredentialsMatcher simpleCredentialsMatcher = new SimpleCredentialsMatcher();

        HashedCredentialsMatcher mather = new HashedCredentialsMatcher();
        mather.setHashAlgorithmName(Constant.ALGORITH_NAME);
        //密码进行两次运算
        mather.setHashIterations(Constant.HASH_ITERATIONS);
        this.setCredentialsMatcher(mather);
    }

    /**
     *
     * 获取用户权限，角色信息进行配置
     *
     * **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("用户角色，权限开始分配");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysPermissionService permissionService = ApplicationContextRegister.getBean(SysPermissionService.class);
        SysRoleService roleService = ApplicationContextRegister.getBean(SysRoleService.class);
        int user_id = ShiroUtils.getUser().getId();
        authorizationInfo.addStringPermissions(permissionService.listPerms(user_id));
        authorizationInfo.setRoles(roleService.getSysRoleByUserId(user_id));
        return authorizationInfo;
    }

    /**
     *
     * 进行常规的信息验证
     *
     * **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("用户认证开始");
        String account = (String) token.getPrincipal();

        SysUserServiceImpl userService = ApplicationContextRegister.getBean(SysUserServiceImpl.class);
        // 查询用户信息
        SysUser user = userService.getUserByAccount(account);

        // 账号不存在1
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }
        // 账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已锁定,请联系管理员");
        }
        // 使用账号作为盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(Constant.PWD_SALT + account);

        // 根据用户的情况，来构建AuthenticationInfo对象,通常使用的实现类为SimpleAuthenticationInfo
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
        return authenticationInfo;
    }
}
