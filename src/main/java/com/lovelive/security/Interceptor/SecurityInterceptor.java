package com.lovelive.security.Interceptor;

import com.lovelive.jwt.utils.JwtUtils;
import com.lovelive.security.auth.Subject;
import com.lovelive.security.utils.SecurityUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限控制拦截器
 *
 * @author dHe
 */
@Component
public class SecurityInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

        Claims claims = JwtUtils.checkToken(request);
        if (claims != null) {

            Subject subject = SecurityUtils.getSubject();

            return true;
        }

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return false;

    }

}
