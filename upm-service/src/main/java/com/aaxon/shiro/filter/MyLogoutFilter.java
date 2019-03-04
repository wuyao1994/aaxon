package com.aaxon.shiro.filter;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author elviswu
 */
public class MyLogoutFilter extends LogoutFilter {
	private Logger log = LoggerFactory.getLogger(MyLogoutFilter.class);



	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);

        try {
            subject.logout();
        } catch (SessionException e) {
            log.debug("Encountered session exception during logout.  This can generally safely be ignored.", e);
        }
        return false;
	}
}
