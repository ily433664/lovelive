package com.lovelive.sys.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * perms or AuthorizationFilter
 */
public class PermsOrAuthorizationFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletrequest, ServletResponse servletresponse,
                                      Object mappedValue) throws Exception {

        Subject subject = getSubject(servletrequest, servletresponse);

        String[] permsArray = (String[]) mappedValue;
        if (permsArray == null || permsArray.length == 0) {
            return true;
        }
        for (int i = 0; i < permsArray.length; i++) {
            if (subject.isPermitted(permsArray[i])) {
                return true;
            }
        }
        return false;
    }
}
