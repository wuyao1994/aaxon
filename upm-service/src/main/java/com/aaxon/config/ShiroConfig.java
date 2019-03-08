package com.aaxon.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.aaxon.shiro.filter.MyFromAuthenticationFilter;
import com.aaxon.shiro.filter.MyLogoutFilter;
import com.aaxon.shiro.filter.MyUserFilter;
import com.aaxon.shiro.realm.ShiroRealm;

/**
 * @author elviswu
 */
@Configuration
public class ShiroConfig {
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setLoginUrl("/user/login");
		shiroFilter.setSuccessUrl("/index");
		Map<String, String> filterChainDefinitionMapping = new HashMap<>();
		filterChainDefinitionMapping.put("/user/login", "authc");
		filterChainDefinitionMapping.put("/user/logout", "logout");
		filterChainDefinitionMapping.put("/user", "anon");
		filterChainDefinitionMapping.put("/login", "anon");
		filterChainDefinitionMapping.put("/**", "user");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
		shiroFilter.setSecurityManager(securityManager());
		Map<String, Filter> filters = new HashMap<>();
		filters.put("anon", new AnonymousFilter());
		filters.put("authc", new MyFromAuthenticationFilter());
		filters.put("logout", new MyLogoutFilter());
		filters.put("user", new MyUserFilter());
		filters.put("roles", new RolesAuthorizationFilter());
		shiroFilter.setFilters(filters);

		return shiroFilter;

	}

	/**
	 * 实现了Initializable或者Destroyable的shiro对象将会自动调用init() and/or destory()方法
	 * 所以这个Bean需要使用 @DependsOn 在其它对象之前初始化
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public EhCacheManager ehCacheManager() {
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManagerConfigFile("classpath:security/ehcache-shiro.xml");
		return ehCacheManager;
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public ShiroRealm realm() {
		return new ShiroRealm();
	}

	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public SimpleCookie rememberMeCookie() {
		SimpleCookie rememberCookie = new SimpleCookie("rememberMe");
		rememberCookie.setHttpOnly(true);
		rememberCookie.setMaxAge(86400);
		return rememberCookie;
	}

	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
		rememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		rememberMeManager.setCookie(rememberMeCookie());
		return rememberMeManager;
	}

	@Bean(name = "securityManager")
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm());
		securityManager.setCacheManager(ehCacheManager());
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}
}
