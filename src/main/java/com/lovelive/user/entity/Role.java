package com.lovelive.user.entity;

import com.lovelive.sys.base.BaseEntity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: Role
 * @Description: 角色
 * @date 2018年1月18日 下午6:10:31
 */

public class Role extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -2480572926205073600L;

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 拥有此角色的用户
     */
    private Set<UserRole> userRoles = new HashSet<>();

    /**
     * 此角色拥有的操作
     */
    private Set<RolePerm> rolePerms = new HashSet<RolePerm>();

    public Role() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<RolePerm> getRolePerms() {
        return rolePerms;
    }

    public void setRolePerms(Set<RolePerm> rolePerms) {
        this.rolePerms = rolePerms;
    }

}
