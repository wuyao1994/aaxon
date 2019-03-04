package com.aaxon.util;

import com.aaxon.domain.Menu;
import com.aaxon.domain.ShiroUser;
import org.apache.shiro.SecurityUtils;

import java.util.List;

/**
 * @author elviswu
 */
public class Securitys extends SecurityUtils {
	public static ShiroUser getUser() {
		if (isAuthenticatedOrRemembered()) {
			return (ShiroUser) getSubject().getPrincipal();
		}

		return new ShiroUser();
	}



	public static boolean isAuthenticatedOrRemembered() {
		return getSubject().isAuthenticated() || getSubject().isRemembered();
	}



	public static List<Menu> getMenus() {
		return getUser().getMenus();
	}



	public static String getName() {
		return getUser().getName();
	}



	public static String getAccountId() {
		return getUser().getAccountId();
	}



	public static boolean isAdmin() {
		return getUser().isAdmin();
	}
}
