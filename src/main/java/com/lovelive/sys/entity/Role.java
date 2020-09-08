package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 角色
 *
 * @author dHe
 */
@Entity(name = "t_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1942590766310951367L;

    /**
     * 角色类型
     * RoleTypeEnums
     */
    @NotBlank
    private String roleType;

    /**
     * 代码
     */
    @NotBlank
    private String code;

    /**
     * 名称
     */
    @NotBlank
    private String name;

    /**
     * 拥有此角色的用户
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new LinkedHashSet<>();

    /**
     * 此角色拥有的操作
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Set<RolePermission> rolePermissions = new LinkedHashSet<>();

    public Role() {
        super();
    }

    public Role(Long id) {
        super(id);
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
