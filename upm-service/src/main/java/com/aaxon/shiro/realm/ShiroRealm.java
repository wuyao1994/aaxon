package com.aaxon.shiro.realm;

import com.aaxon.domain.ShiroUser;
import com.aaxon.service.UpmsApiService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author elviswu
 */
public class ShiroRealm extends AuthorizingRealm {
	private UpmsApiService mUpmsApiService;



	/**
	 * 授权：验证是调用
	 * 
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(shiroUser.getPermissions());
		info.addRoles(shiroUser.getRoles());
		return info;
	}



	/**
	 * 认证：登录时调用
	 * 
	 * @param authenticationToken
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
		String username = usernamePasswordToken.getUsername();
		String password = new String(usernamePasswordToken.getPassword());
		ShiroUser shiroUser = mUpmsApiService.login(username, password);
		return new SimpleAuthenticationInfo(shiroUser, usernamePasswordToken.getPassword(), getName());
	}
}
