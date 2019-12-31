package com.lovelive.sys.entity;

import com.lovelive.common.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色
 *
 * @author dHe
 * @date 2019-4-26
 */
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 3547529138085233166L;

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
    private List<UserRole> userRoles = new ArrayList<>();

    /**
     * 此角色拥有的操作
     */
    @OneToMany(mappedBy = "role", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<RolePermission> rolePermissions = new ArrayList<>();

    public Role() {
        super();
    }

    public Role(String id) {
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

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(List<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
