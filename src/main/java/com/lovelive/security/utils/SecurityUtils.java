package com.lovelive.security.utils;


import com.lovelive.jwt.utils.JwtUtils;
import com.lovelive.security.auth.Subject;

/**
 * 权限 utils
 *
 * @author dHe
 */
public class SecurityUtils {

    public SecurityUtils() {
    }

    public static Subject getSubject() {
        Subject subject = ThreadContext.getSubject();
        if (subject == null) {
            JwtUtils.getToken();
            subject = Subject.getInstance();
            ThreadContext.bind(subject);
        }

        return subject;
    }
}
