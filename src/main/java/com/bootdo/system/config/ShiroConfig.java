package com.bootdo.system.config;

import com.bootdo.system.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lihq on 2018/9/29 10:09
 * Description: Shiro配置类
 */

@Configuration
public class ShiroConfig {

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * @return

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
     */

    /**
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        bean.setLoginUrl("/login");                     // 必须设置LoginUrl 否则会到根目录下找login.jsp页面
        Map<String, String> filterChainMap = new HashMap<>();
        filterChainMap.put("/login", "anon");

        filterChainMap.put("/chart/**", "anon");
        filterChainMap.put("/diagram-viewer/**", "anon");

        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/docs/**", "anon");
        filterChainMap.put("/editor-app/**", "anon");
        filterChainMap.put("/fonts/**", "anon");
        filterChainMap.put("/img/**", "anon");
        filterChainMap.put("/js/**", "anon");

        filterChainMap.put("/index", "anon");
        filterChainMap.put("/**", "authc");
        bean.setFilterChainDefinitionMap(filterChainMap);
        return bean;
    }

    /**
     * 安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    /**
     * 用户域
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
