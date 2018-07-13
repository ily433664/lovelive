package com.lovelive.sys.config;

import com.lovelive.sys.security.LocalRealm;
import com.lovelive.sys.security.PermsOrAuthorizationFilter;
import com.lovelive.sys.security.RolesOrAuthorizationFilter;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dhe
 * @Description Shiro配置
 * @date 2018年3月21日
 */
@Configuration
public class ShiroConfig {

    // 将自己的验证方式加入容器
    @Bean
    public LocalRealm localRealm() {
        return new LocalRealm();
    }

    // 用户授权信息Cache, 采用EhCache
    @Bean
    public EhCacheManager shiroCacheManager() {
        EhCacheManager shiroCacheManager = new EhCacheManager();
        shiroCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
        return shiroCacheManager;
    }

    // 权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(localRealm());
        securityManager.setCacheManager(shiroCacheManager());
        return securityManager;
    }


    // Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 首页
        shiroFilterFactoryBean.setSuccessUrl("/loginAssign");
        // 错误页面，认证不通过跳转
        //shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("rolesOr", new RolesOrAuthorizationFilter());
        filterMap.put("permsOr", new PermsOrAuthorizationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> mapping = new LinkedHashMap<>();
        mapping.put("/login", "authc");
        mapping.put("/logout", "logout");
        //mapping.put("/test/**", "authc");

        mapping.put("/sys/**", "rolesOr[root, manage]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(mapping);

        return shiroFilterFactoryBean;
    }

    // 加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
