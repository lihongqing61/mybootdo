package com.bootdo.system.shiro;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.MenuService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Set;

/**
 * Created by Lihq on 2018/9/29 10:13
 * Description: UserRealm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MenuService menuService;
    /**
     * 登录认证
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        UserDO user = userDao.findUserByUsername(username);

        // 账号不存在
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException("账号不存在");
        }

        // 密码不正确
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名/密码不正确");
        }

        // 账号锁定
        if (user.getStatus() == 0) {
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Set<String> userPermSet = menuService.listUserPerms(ShiroUtils.getUserId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(userPermSet);
        return info;
    }
}
