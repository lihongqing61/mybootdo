package com.bootdo.common.utils;

import com.bootdo.system.domain.UserDO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;


/**
 * Created by Lihq on 2018/9/29 13:47
 * Description: Shiro工具类
 */
public class ShiroUtils {

    @Autowired
    private static SessionDAO sessionDAO;

    /**
     * 获取主体
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public static UserDO getUser() {
        return (UserDO) getSubject().getPrincipal();
    }

    /**
     * 获取当前登录用户id
     * @return
     */
    public static Long getUserId() {
        return getUser().getUserId();
    }

    /**
     * 退出
     */
    public static void logout() {
        getSubject().logout();
    }

    public static Collection<Session> getPrincipals() {
        return sessionDAO.getActiveSessions();

    }
}
