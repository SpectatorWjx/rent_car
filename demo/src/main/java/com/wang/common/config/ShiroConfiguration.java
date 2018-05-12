package com.wang.common.config;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro 配置
 *
 Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 *
 * @author Angel(QQ:412887952)
 * @version v.0.1
 */
@Configuration
public class ShiroConfiguration {


    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     Filter Chain定义说明
     1、一个URL可以配置多个Filter，使用逗号分隔
     2、当设置多个过滤器时，全部验证通过，才视为通过
     3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);



        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();

        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/user/logout", "logout");
        filterChainDefinitionMap.put("/test/*", "anon");
        filterChainDefinitionMap.put("/uploadimg", "anon");
        filterChainDefinitionMap.put("/picture/**", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/adminLogin", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");
        filterChainDefinitionMap.put("/user/sendCaptcha", "anon");

        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");

        // 如果不设置默认会访问noShiro
        shiroFilterFactoryBean.setLoginUrl("/noShiro");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public UserRealm getUserRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    @Bean
    public AdminRealm getAdminRealm() {
        AdminRealm adminRealm = new AdminRealm();
        return adminRealm;
    }
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setAuthenticator(modularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        //添加多个Realm
        realms.add(getAdminRealm());
        realms.add(getUserRealm());
        securityManager.setRealms(realms);
        //注入记住我管理器;
        return securityManager;
    }

    @Bean
    public MyModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        MyModularRealmAuthenticator modularRealmAuthenticator = new MyModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

}