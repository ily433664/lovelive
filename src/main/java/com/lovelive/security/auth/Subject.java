package com.lovelive.security.auth;

import com.lovelive.common.uitls.StringUtils;
import com.lovelive.jwt.utils.JwtUtils;
import com.lovelive.sys.entity.Permission;
import com.lovelive.sys.entity.Role;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 认证
 *
 * @author dHe
 */
public final class Subject implements java.io.Serializable {

    private static final long serialVersionUID = -3187953979756790307L;

    private static final Map<Object, Object> resources = new ConcurrentHashMap<>();

    protected String token;

    protected Principal principal;

    protected boolean authenticated;

    private Subject(String token) {
        this.token = token;
    }

    public static Subject getInstance() {
        String token = JwtUtils.getToken();
        if (StringUtils.isNotEmpty(token)) {
            if (Subject.resources.containsKey(token)) {
                return (Subject) Subject.resources.get(token);
            } else {
                Subject subject = new Subject(token);
                Subject.resources.put(token, subject);
                return subject;
            }
        }
        return new Subject(token);
    }

    public boolean checkToken() {
        return JwtUtils.checkToken(token) != null;
    }

    public boolean login() {
        return true;
    }

    public Object getPrincipal() {
        //TODO
        return null;
    }

    public boolean isPermitted(String permission) {
        //TODO
        return false;
    }

    public boolean isPermitted(Permission permission) {
        //TODO
        return false;
    }

    public boolean isPermittedAny(String... permissions) {
        //TODO
        return false;
    }

    public boolean isPermittedAny(Collection<Permission> permissions) {
        //TODO
        return false;
    }

    public boolean isPermittedAll(String... permissions) {
        //TODO
        return false;
    }

    public boolean isPermittedAll(Collection<Permission> permissions) {
        //TODO
        return false;
    }

    public boolean hasRole(String role) {
        //TODO
        return false;
    }

    public boolean hasRole(Role role) {
        //TODO
        return false;
    }

    public boolean hasRoleAny(String... roles) {
        //TODO
        return false;
    }

    public boolean hasRoleAny(Collection<Role> roles) {
        //TODO
        return false;
    }

    public boolean hasRoleAll(String... roles) {
        //TODO
        return false;
    }

    public boolean hasRoleAll(Collection<Role> roles) {
        //TODO
        return false;
    }

    public String getToken() {
        return token;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
